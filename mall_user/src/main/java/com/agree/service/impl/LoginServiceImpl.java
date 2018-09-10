package com.agree.service.impl;

import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.mapper.LoginMapper;
import com.agree.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper loginMapper;

	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		return loginMapper.select_user(user);
	}

}
