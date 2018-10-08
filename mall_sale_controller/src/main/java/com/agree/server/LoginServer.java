package com.agree.server;

import com.agree.bean.T_MALL_USER_ACCOUNT;

import javax.jws.WebService;

@WebService
public interface LoginServer {
    public String login(T_MALL_USER_ACCOUNT user);
    public String login2(T_MALL_USER_ACCOUNT user);
}
