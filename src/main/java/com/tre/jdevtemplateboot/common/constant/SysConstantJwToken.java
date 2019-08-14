package com.tre.jdevtemplateboot.common.constant;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-28 09:11
 **/
public class SysConstantJwToken {

    public static String JWT_AUTHOR_TYPE = "Bearer ";

    //token有效时间,单位毫秒 5小时候后失效
    public static long JWT_TTL = 5*60*60*1000;

    //密匙
    public static String JWT_SECERT = "46cc793c53dc451b8a4fe2cd0bb008471";
}
