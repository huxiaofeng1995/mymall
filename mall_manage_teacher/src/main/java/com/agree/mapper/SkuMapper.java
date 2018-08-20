package com.agree.mapper;

import com.agree.bean.T_MALL_SKU;
import com.agree.bean.T_MALL_SKU_ATTR_VALUE;

import java.util.Map;

public interface SkuMapper {

    public void insert_sku(T_MALL_SKU sku);

    public void insert_sku_attr_value(Map<Object,Object> map);
}


