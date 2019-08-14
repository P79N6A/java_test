package com.tre.jdevtemplateboot.common.pojo;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 09:52
 **/
public class TokenModelBean {

    private String userId;
    private String token;

    public TokenModelBean(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
