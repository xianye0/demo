package com.example.demo.applications.service.impl;

import com.example.demo.applications.entity.Authority;
import com.example.demo.applications.entity.AuthorityUrl;
import com.example.demo.applications.entity.Operator;
import com.example.demo.applications.exception.ResponseException;
import com.example.demo.applications.mapper.SecurityMapper;
import com.example.demo.applications.utils.UserUtils;
import com.example.demo.plugins.message.response.ResponsesBuilder;
import com.example.demo.plugins.model.response.ResponseCode;
import com.example.demo.plugins.utils.common.CommonStringUtils;
import com.example.demo.plugins.utils.security.KeyPairManager;
import com.example.demo.plugins.utils.security.PasswordKey;
import com.example.demo.plugins.utils.security.PasswordUtils;
import com.example.demo.plugins.utils.tree.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description: 登录相关
 */
@Slf4j
@Service
public class SecurityService {
    @Autowired
    ResponsesBuilder responsesBuilder;
    @Autowired
    UserService userService;
    @Autowired
    SecurityMapper securityMapper;
    @Autowired
    PasswordUtils passwordUtils;
    @Autowired
    UserUtils userUtils;
    @Autowired
    KeyPairManager keyPairManager;


    /**
     * 获取公钥
     * @return
     */
    public PasswordKey getPublicKey() {
        String key = createToken();
        return keyPairManager.getPublicKey(key);
    }

    /**
     * 登录
     * @param username
     * @param pwd
     * @param key
     * @return
     */
    public String login(String username, String pwd, String key) {
        Integer codeEnum;
        Map userInfo = new HashMap();
        log.info("Start get {} userInfo.", username);
        //获取用户信息
        Operator user = securityMapper.getByUsername(username);
        if (user == null) {
            throw new ResponseException(ResponseCode.USERNAME_NOTEXISTS);
        }

        //校验用户密码并查询用户相关信息，如果未通过则返回错误码
        codeEnum = checkUser(key, pwd, user, false);
        if (codeEnum != null) {
            log.warn("Check user not access code is {}.", codeEnum);
            throw new ResponseException(codeEnum);
        }
        //获取用户权限
        String token = handleAuthority(user, userInfo);
        if (token != null) {
            if (user.getModifyPassTime() == null) {
                ResponseException e = new ResponseException(ResponseCode.NEED_UPDATEPWD);
                e.setData(token);
                throw e;
            }
            return token;
        } else {
            throw new ResponseException(ResponseCode.ACCOUNT_AUTH_ERROR);
        }

    }

    /**
     * 获取菜单
     * @param token
     * @return
     */
    public List<Authority> queryMenuList(String token) {
        return userUtils.getMenu(token);
    }


    /**
     * 注销
     * @param token
     */
    public void logout(String token) {
        userUtils.remove(token);
    }

    public Operator getOperator(String token){
        return userUtils.getOperator(token);
    }

    public void modOperator(String token, Operator operator) {
        userService.mod(operator);
        Operator currOper = userUtils.getOperator(token);
        currOper.setName(operator.getName());
        currOper.setPhone(operator.getPhone());
        currOper.setEmail(operator.getEmail());
        userUtils.setOperator(token, currOper);
    }

    /**
     * 判断是否拥有权限
     * @param token
     * @param url
     * @return
     */
    public int checkAuth(String token, String url) {
        try {
            if (userUtils.getOperateAuths(token).containsKey(url)) {
                return ResponseCode.SUCCESS;
            }
            return ResponseCode.AUTH_FAILED;
        } catch (ResponseException e) {
            return ResponseCode.TOKEN_INVALID;
        }
    }

    public Map<String, AuthorityUrl> queryUrlMap() {
        return securityMapper.queryUrlMap();
    }


    private Integer checkUser(String key, String pwd, Operator user, boolean isDecrypt) {
        log.info("Start check user.");
        String password = passwordUtils.handlePwd(key, user.getUsername(), pwd, isDecrypt);
        Integer code = null;
        if (!validatePassword(user, password)) {
            code = ResponseCode.LOGIN_FAILURE;
        }
        return code;
    }

    /**
     * 校验密码
     * @param user
     * @param password
     * @return
     */
    private boolean validatePassword(Operator user, String password) {
        log.info("Start validate pwd.");
        if (!user.getPassword().equals(password)) { //校验密码
            log.warn("The pwd is wrong.");
            return false;
        }
        return true;
    }


    /**
     * 获取用户权限
     *
     * @param user 登录用户
     * @param userInfo 用户信息
     * @return
     */
    private String handleAuthority(Operator user, Map userInfo) {
        log.info("Start get user auth.");
        Map<String, Object> authorityMap = queryAuthorityMap(user.getId());
        if (authorityMap == null) {
            log.warn("The user auth is null.");
        } else {
            userInfo.putAll(authorityMap);
        }

        user.setPassword(null);
        userInfo.put("userInfo", user);

        String token = createToken();
        userUtils.setOperator(token, userInfo);
        //设置最后登录时间
        securityMapper.updateLoginDate(user.getUsername());
        log.info("Set user cache success.");
        return token;
    }

    /**
     * 生成token
     * 当前毫秒加上随机数
     * @return
     */
    private String createToken() {
        return CommonStringUtils.createToken();
    }


    /**
     * 根据角色从数据库查询出所有权限
     * 将权限进行分类封装
     *
     * @param userId
     * @return
     */
    public Map<String, Object> queryAuthorityMap(BigDecimal userId) {
        List<Authority> authorityList = securityMapper.queryAuthorityList(userId);
        Map authMap = new HashMap();
        if (!authorityList.isEmpty()) {
            //将权限分类存入map中
            packageAuthMap(authorityList, authMap);
        }
        return authMap;
    }

    /**
     * 将权限根据要求整理好
     * 1.将权限类型为菜单(0)的按树型结构放入缓存中
     * 2.将菜单和权限的code放入list中
     * 3.将类型为2的权限整理为parentCode的形式放入map中
     *
     * @param authorityList
     * @param authMap
     */
    private void packageAuthMap(List<Authority> authorityList, Map authMap) {
        //权限url，存放格式为<url:method,菜单code>，用来校验是否拥有某个接口的访问权限
        Map operateMap = new HashMap();
        //权限code，存放类型为0，1的权限code，给前端提供的接口，前端根据code来判断是否展示界面上的某些按钮等要素
        List codeList = new ArrayList();
        //存放菜单
        List menuList = new ArrayList();

        for (Authority auth : authorityList) {
            switch (auth.getType()) {
                case 0:
                    menuList.add(auth);
                    codeList.add(auth.getCode());
                    putPermissions(auth.getAuthorities(), operateMap, auth.getCode());
                    break;
                case 1:
                    codeList.add(auth.getCode());
                    putPermissions(auth.getAuthorities(), operateMap, auth.getCode());
                    break;
                default:
            }
        }
        authMap.put("operateAuth", operateMap);
        //封装菜单树
        List menu = TreeUtils.listToTree(menuList);
        authMap.put("menuTree", menu);
        authMap.put("authCodes", codeList);
    }


    /**
     * 获取权限的code并放入map中
     * @param permissions
     * @param map
     */
    private void putPermissions(String permissions, Map map, Object authKey) {
        if (StringUtils.isNotBlank(permissions)) {
            for (String permission : permissions.split(",")) {
                map.put(permission, authKey);
            }
        }
    }


}
