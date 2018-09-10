package com.agree.server.impl;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.server.LoginServer;
import com.agree.service.LoginService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginServerImpl implements LoginServer {

	@Autowired
	LoginService loginService;

	@Override
	@Path("login")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String login(@BeanParam T_MALL_USER_ACCOUNT user) {
		T_MALL_USER_ACCOUNT select_user = loginService.login(user);
		return JSONObject.toJSONString(select_user);
	}

}
