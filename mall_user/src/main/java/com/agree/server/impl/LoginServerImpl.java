package com.agree.server.impl;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.server.LoginServer;
import com.agree.service.LoginService;
import com.agree.utils.MyRoutingDataSource;
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
		// 数据源1
		MyRoutingDataSource.setKey("1");
		T_MALL_USER_ACCOUNT select_user = loginService.login(user);
		return JSONObject.toJSONString(select_user);
	}

	@Override
	@Path("login2")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String login2(T_MALL_USER_ACCOUNT user) {
		// 数据源2
		MyRoutingDataSource.setKey("2");
		T_MALL_USER_ACCOUNT select_user = loginService.login2(user);
		return JSONObject.toJSONString(select_user);
	}

}
