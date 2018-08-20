package com.agree.service;

import com.agree.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.agree.bean.T_MALL_PRODUCT;
import com.agree.bean.T_MALL_SKU;
import com.agree.bean.T_MALL_SKU_ATTR_VALUE;

import java.util.List;

public interface SkuService {

   public void save_sku(List<T_MALL_SKU_ATTR_VALUE> list_attr, T_MALL_SKU sku, T_MALL_PRODUCT spu);
}
