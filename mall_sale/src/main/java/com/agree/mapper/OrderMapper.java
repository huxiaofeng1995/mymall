package com.agree.mapper;

import com.agree.bean.OBJECT_T_MALL_FLOW;
import com.agree.bean.OBJECT_T_MALL_ORDER;
import com.agree.bean.T_MALL_ORDER_INFO;

import java.util.Map;

public interface OrderMapper {
    void insert_order(OBJECT_T_MALL_ORDER order);

    void insert_flow(OBJECT_T_MALL_FLOW flow);

    void insert_infos(Map<Object, Object> map_info);

    long select_kc(Map<Object, Object> map);

    void update_kc(T_MALL_ORDER_INFO info);

    void update_order(OBJECT_T_MALL_ORDER order);

    void update_flow(OBJECT_T_MALL_FLOW flow);

    int select_count_kc(int sku_id);//查询该sku的库存是否大于10个
}
