package com.agree.mapper;

import java.util.List;
import java.util.Map;

import com.agree.bean.T_MALL_ADDRESS;
import com.agree.bean.T_MALL_USER_ACCOUNT;

public interface UserMapper {


	List<T_MALL_USER_ACCOUNT> findByName(T_MALL_USER_ACCOUNT user);

	void insert(T_MALL_USER_ACCOUNT user);

	List<T_MALL_ADDRESS> select_addresses(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS select_address(int address_id);

	void insert_address(Map<String, Object> map);
}
