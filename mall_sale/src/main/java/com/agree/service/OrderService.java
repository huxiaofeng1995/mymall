package com.agree.service;

import com.agree.bean.OBJECT_T_MALL_ORDER;
import com.agree.bean.T_MALL_ADDRESS;

public interface OrderService {
    void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS addr);
}
