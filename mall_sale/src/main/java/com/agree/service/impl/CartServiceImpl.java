package com.agree.service.impl;

import com.agree.bean.T_MALL_SHOPPINGCAR;
import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.mapper.CartMapper;
import com.agree.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public boolean if_cart_exists(T_MALL_SHOPPINGCAR cart) {
        int count = cartMapper.select_count(cart);
        if(count > 0){
            return true;
        }
        return false;
    }

    @Override
    public void add_cart(T_MALL_SHOPPINGCAR cart) {
        cartMapper.insert_cart(cart);
    }

    @Override
    public void update_cart(T_MALL_SHOPPINGCAR cart) {
        cartMapper.update_cart(cart);
    }

    @Override
    public List<T_MALL_SHOPPINGCAR> get_cart_list_by_user(T_MALL_USER_ACCOUNT user) {
        return cartMapper.select_list_cart_by_user(user);
    }

    @Override
    public void delete_cart(T_MALL_SHOPPINGCAR t_mall_shoppingcar) {
        cartMapper.delete_cart(t_mall_shoppingcar);
    }
}
