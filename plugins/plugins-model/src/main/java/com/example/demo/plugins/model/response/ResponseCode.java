package com.example.demo.plugins.model.response;

/**
 * @author: rock
 * @date: 2019/1/31
 * @Description:
 */
public class ResponseCode {

    public static final int SUCCESS = 0;


    // 修改密码
    public static final int NEED_UPDATEPWD = 1;

    // 参数校验失败
    public static final int PARAM_INVALID = 1001;

    // 身份验证失败
    public static final int LOGIN_FAILURE = 2001;

    // TOKEN失效
    public static final int TOKEN_INVALID = 2002;

    // 账号不存在
    public static final int USERNAME_NOTEXISTS = 2003;

    // 账号停用
    public static final int ACCOUNT_HALT = 2004;

    // 登录次数过多
    public static final int LOGIN_ERROR = 2005;

    // 登录次数过多
    public static final int ACCOUNT_AUTH_ERROR = 2006;
    // 无接口权限
    public static final int AUTH_FAILED = 2007;

    // 账号未绑定
    public static final int ACCOUNT_NOT_BINDING = 2008;
    // 账号已存在
    public static final int USERNAME_ALREADYEXISTS = 2009;

    // 验证码错误
    public static final int VERIFYCODE_ERROR = 2010;
    // 验证码过期
    public static final int VERIFYCODE_NOTEXISTS = 2011;

    // 密码输入错误，请重新输入
    public static final int PASSWORD_ERROR = 2012;

    // 密码错误次数过多，请联系管理员处理
    public static final int PASSWORD_ERROR_MUCH = 2013;


    public static final int SYSTEM_ERROR = 9999;
}
