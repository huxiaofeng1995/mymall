package com.agree.server;

import com.agree.bean.T_MALL_ADDRESS;
import com.agree.bean.T_MALL_USER_ACCOUNT;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserServer {
    public List<T_MALL_ADDRESS> get_address_list(T_MALL_USER_ACCOUNT user);

    public T_MALL_ADDRESS get_address(int addressId);

    public void add_address(T_MALL_ADDRESS address, int yh_id);

    public String exist(T_MALL_USER_ACCOUNT user);

    public void registry(T_MALL_USER_ACCOUNT user);
}
