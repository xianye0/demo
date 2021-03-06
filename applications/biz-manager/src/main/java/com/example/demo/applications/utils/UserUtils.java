package com.example.demo.applications.utils;

import com.example.demo.applications.entity.Authority;
import com.example.demo.applications.entity.Operator;
import com.example.demo.applications.exception.ResponseException;
import com.example.demo.plugins.model.response.ResponseCode;
import com.example.demo.plugins.utils.memory.ICacheMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
@Component
public class UserUtils {
    private int TIMEOUT = 30;

    private static final String PRE = "USER_";
    @Autowired
    private ICacheMap<String, Object> map;

    public UserUtils() {

    }

    public UserUtils(ICacheMap map) {
        this.map = map;
    }

    public void setOperator(String key, Map<String, Operator> operator) {
        map.put(PRE + key, operator, TIMEOUT * 1000);
    }

    public void setOperator(String key, Operator operator) {
        map.putHash(PRE + key,"userInfo", operator);
    }

    public Map getOperatorMap(String key) {
        Map o = (Map)map.get(PRE + key);
        if (o == null) {
            throw new ResponseException(ResponseCode.TOKEN_INVALID);
        }
        return o;
    }

    public Operator getOperator(String key) {
        return (Operator) getOperatorMap(key).get("userInfo");
    }

    public List<Authority> getMenu(String key) {
        return (List<Authority>) getOperatorMap(key).get("menuTree");
    }

    public void remove(String key) {
        map.remove(PRE + key);
    }

    public List<String> getAuthCodes(String key) {
        return (List<String>) getOperatorMap(key).get("authCodes");
    }


    public Map getOperateAuths(String key) {
        return (Map) getOperatorMap(key).get("operateAuth");
    }



}
