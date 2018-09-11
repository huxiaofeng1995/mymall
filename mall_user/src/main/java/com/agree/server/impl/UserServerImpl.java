package com.agree.server.impl;

import com.agree.bean.T_MALL_ADDRESS;
import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.server.UserServer;
import com.agree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServerImpl implements UserServer{

    @Autowired
    private UserService userService;

    @Override
    public List<T_MALL_ADDRESS> get_address_list(T_MALL_USER_ACCOUNT user) {
        return userService.get_address_list(user);
    }

    @Override
    public T_MALL_ADDRESS get_address(int addressId) {
        return userService.get_address(addressId);
    }

    @Override
    public void add_address(T_MALL_ADDRESS address, int yh_id) {
        userService.add_address(address, yh_id);
    }

    @Override
    public String exist(T_MALL_USER_ACCOUNT user) {
        List<T_MALL_USER_ACCOUNT> list_user = userService.findByName(user);
        if(list_user != null && list_user.size() > 0){
            return "true";
        }
        return "false";
    }

    @Override
    public void registry(T_MALL_USER_ACCOUNT user) {
        userService.registry(user);
    }
}
