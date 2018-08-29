package com.model;

import com.bean.user.User;

public class LoginUserModel {

    //登录用户的基本信息
    private User user;

    //登录用户的IP
    private String clientIp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
