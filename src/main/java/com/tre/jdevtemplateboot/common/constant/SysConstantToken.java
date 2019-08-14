package com.tre.jdevtemplateboot.common.constant;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 10:15
 **/
public class SysConstantToken {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 24;

    /**
     * 存放token的header字段
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 存放token的header字段
     */
    public static final String AUTHOR_TYPE = "Bearer ";


    /**
     * 存放token的User信息前缀
     */
    public static final String PRE_FIX_USER = "USER_TOKEN:";
}
