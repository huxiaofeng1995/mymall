package com.agree.service;

import com.agree.bean.MODEL_CLASSNAME;
import com.agree.bean.OBJECT_T_MALL_SKU;
import com.agree.bean.T_MALL_SKU_ATTR_VALUE;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ListService {

    public List<OBJECT_T_MALL_SKU> get_sku_list(int flbh2);

    List<OBJECT_T_MALL_SKU> get_sku_list_by_attr(List<T_MALL_SKU_ATTR_VALUE> list_attr, int flbh2);

    MODEL_CLASSNAME get_classname(int flbh2);
}
