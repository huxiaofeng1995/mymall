package com.agree.service;

import com.agree.bean.*;

import java.util.List;

public interface SkuService {

   public void save_sku(List<T_MALL_SKU_ATTR_VALUE> list_attr, T_MALL_SKU sku, T_MALL_PRODUCT spu, T_MALL_PRODUCT_SKU_INFO sku_info);
}
