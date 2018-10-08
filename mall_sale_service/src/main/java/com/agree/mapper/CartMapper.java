package com.agree.mapper;

import com.agree.bean.T_MALL_SHOPPINGCAR;
import com.agree.bean.T_MALL_USER_ACCOUNT;

import java.util.List;
import java.util.Map;

public interface CartMapper {
    int select_count(T_MALL_SHOPPINGCAR cart);

    void insert_cart(T_MALL_SHOPPINGCAR cart);

    void update_cart(T_MALL_SHOPPINGCAR cart);

    List<T_MALL_SHOPPINGCAR> select_list_cart_by_user(T_MALL_USER_ACCOUNT user);

    void delete_cart(T_MALL_SHOPPINGCAR t_mall_shoppingcar);

    void delete_carts(Map<Object, Object> map);
}
