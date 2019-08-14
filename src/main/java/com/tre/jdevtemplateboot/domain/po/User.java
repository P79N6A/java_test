package com.tre.jdevtemplateboot.domain.po;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 14:44
 **/
public class User {

    private String Id;
    /* 用户名*/
    private String userName;
    /* 密码*/
    private String passWord;
    /* 用户角色   1  普通用户，2 管理员*/
    private int role;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + Id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", role=" + role +
                '}';
    }
}
