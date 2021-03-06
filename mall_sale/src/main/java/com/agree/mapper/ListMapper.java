package com.agree.mapper;

import com.agree.bean.MODEL_CLASSNAME;
import com.agree.bean.OBJECT_T_MALL_SKU;

import java.util.List;
import java.util.Map;

public interface ListMapper {
    public List<OBJECT_T_MALL_SKU> select_list_sku(int flbh2);

    List<OBJECT_T_MALL_SKU> select_list_sku_by_attr(Map<String, Object> map);

    MODEL_CLASSNAME select_classname(int flbh2);
}
