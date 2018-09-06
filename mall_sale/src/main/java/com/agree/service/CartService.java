package com.agree.service;

import com.agree.bean.T_MALL_SHOPPINGCAR;

public interface CartService {
    boolean if_cart_exists(T_MALL_SHOPPINGCAR cart);

    void add_cart(T_MALL_SHOPPINGCAR cart);

    void update_cart(T_MALL_SHOPPINGCAR t_mall_shoppingcar);
}
