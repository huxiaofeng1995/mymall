package com.agree.mapper;

import com.agree.bean.T_MALL_USER_ACCOUNT;

public interface LoginMapper {

	T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);

	T_MALL_USER_ACCOUNT select_user_by_username(T_MALL_USER_ACCOUNT user);

}
