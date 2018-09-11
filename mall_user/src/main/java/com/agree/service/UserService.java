package com.agree.service;

import com.agree.bean.T_MALL_ADDRESS;
import com.agree.bean.T_MALL_USER_ACCOUNT;

import java.util.List;

public interface UserService {
    public List<T_MALL_ADDRESS> get_address_list(T_MALL_USER_ACCOUNT user);

    public T_MALL_ADDRESS get_address(int addressId);

    public void add_address(T_MALL_ADDRESS address,int yh_id);

    public List<T_MALL_USER_ACCOUNT> findByName(T_MALL_USER_ACCOUNT user);

    public void registry(T_MALL_USER_ACCOUNT user);
}
