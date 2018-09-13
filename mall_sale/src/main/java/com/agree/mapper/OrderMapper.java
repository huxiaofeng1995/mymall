package com.agree.mapper;

import com.agree.bean.OBJECT_T_MALL_FLOW;
import com.agree.bean.OBJECT_T_MALL_ORDER;

import java.util.Map;

public interface OrderMapper {
    void insert_order(OBJECT_T_MALL_ORDER order);

    void insert_flow(OBJECT_T_MALL_FLOW flow);

    void insert_infos(Map<Object, Object> map_info);
}
