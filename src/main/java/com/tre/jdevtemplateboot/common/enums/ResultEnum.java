package com.tre.jdevtemplateboot.common.enums;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 08:59
 **/
public enum ResultEnum {

    LOGIN_FAIL("1001", "登录失败, 用户不存在"),
    LOGIN_FAIL_PASS("1002","密码错误"),
    LOGOUT_SUCCESS("1003", "登出成功"),
    LOGIN_FAIL_AUTHENTICATION("401","请登录"),
    LOGIN_FAIL_HAS_TOKEN("1004","请求请附带Token信息"),
    LOGIN_FAIL_VALIDATOR_TOKEN("1005","Token信息不合法，请登录"),
    ;


    private String code;
    private String msg;


    ResultEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum stateOf(String code){
        for (ResultEnum state:values()){
            if (state.getCode()==code){
                return state;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
