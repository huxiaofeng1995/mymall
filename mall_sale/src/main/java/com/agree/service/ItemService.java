package com.agree.service;

import com.agree.bean.DETAIL_T_MALL_SKU;
import com.agree.bean.T_MALL_SKU;

import java.util.List;

public interface ItemService {
    
    List<T_MALL_SKU> get_list_sku_by_spu(int spu_id);

    DETAIL_T_MALL_SKU get_sku_detail(int sku_id);
}
