package com.tre.jdevtemplateboot.common.pojo;

import com.tre.jdevtemplateboot.common.constant.SysConstantToken;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 12:34
 **/
public class UserPrefix extends BasePrefix {

    public UserPrefix(String prefix) {
        super(prefix);
    }

    public UserPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserPrefix getUserPrefix = new UserPrefix(SysConstantToken.TOKEN_EXPIRES_HOUR,
            SysConstantToken.PRE_FIX_USER);
}
