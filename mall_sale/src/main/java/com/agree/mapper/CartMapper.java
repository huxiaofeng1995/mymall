package com.agree.mapper;

import com.agree.bean.T_MALL_SHOPPINGCAR;

public interface CartMapper {
    int select_count(T_MALL_SHOPPINGCAR cart);

    void insert_cart(T_MALL_SHOPPINGCAR cart);

    void update_cart(T_MALL_SHOPPINGCAR cart);
}
