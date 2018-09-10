package com.agree.service.impl;

import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.mapper.LoginMapper;
import com.agree.service.LoginService;
import com.agree.utils.MyRoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper loginMapper;

	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		// 数据源1
		MyRoutingDataSource.setKey("1");
		return loginMapper.select_user(user);
	}

	@Override
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user) {
		// 数据源2
		MyRoutingDataSource.setKey("2");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginMapper.select_user(user);
	}

}
