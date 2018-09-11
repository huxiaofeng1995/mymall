package com.agree.service.impl;

import com.agree.bean.T_MALL_ADDRESS;
import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.mapper.UserMapper;
import com.agree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<T_MALL_ADDRESS> get_address_list(T_MALL_USER_ACCOUNT user) {
        return userMapper.select_addresses(user);
    }

    @Override
    public T_MALL_ADDRESS get_address(int addressId) {
        return userMapper.select_address(addressId);
    }

    @Override
    public void add_address(T_MALL_ADDRESS address, int yh_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("address", address);
        map.put("yh_id", yh_id);
        userMapper.insert_address(map);
    }

    @Override
    public List<T_MALL_USER_ACCOUNT> findByName(T_MALL_USER_ACCOUNT user) {
        return userMapper.findByName(user);
    }

    @Override
    public void registry(T_MALL_USER_ACCOUNT user) {
        userMapper.insert(user);
    }

}
