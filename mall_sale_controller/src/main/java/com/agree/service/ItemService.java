package com.agree.service;

import com.agree.bean.*;

import java.util.List;

public interface ItemService {
    
    List<T_MALL_SKU> get_list_sku_by_spu(int spu_id);

    DETAIL_T_MALL_SKU get_sku_detail(int sku_id);

    OBJECT_T_MALL_PRODUCT get_spu_sale_attr(int spu_id);

    T_MALL_PRODUCT_SKU_INFO get_sku_id(int color, int version);

    OBJECT_PRODUCT_SKU_INFO get_sale_attr_by_skuId(int sku_id);
}
