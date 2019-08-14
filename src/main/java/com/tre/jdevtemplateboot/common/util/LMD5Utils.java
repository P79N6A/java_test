package com.tre.jdevtemplateboot.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: 加密方法
 * @author: JDev
 * @create: 2018-11-22 09:37
 **/
public class LMD5Utils {

    private static final String salt = "1a2b3c4d";

    /**
     * 第一次md5加密，用于网络传输
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return encrypt32(str);
    }

    /**
     * 第二次Md5加密，用于存储
     * @param formPass
     * @return
     */
    public static String formPassToDbPass(String formPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return encrypt32(str);
    }

    /**
     * 合并
     * @param input
     * @return
     */
    public static String inputPassToDbPass(String input){
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDbPass(formPass);
        return dbPass;
    }





    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String encrypt32(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    private static String encrypt32Up(String strObj) {
        return encrypt32(strObj).toUpperCase();
    }

    private static String encrypt16(String strObj) {
        return encrypt32(strObj).substring(8, 24);
    }

    private static String encrypt16Up(String strObj) {
        return encrypt32Up(strObj).substring(8, 24);
    }


    /**
     * 转换字节数组为16进制字串
     */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * 返回形式为数字跟字符串
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

}
