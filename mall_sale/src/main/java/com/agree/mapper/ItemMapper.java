package com.agree.mapper;

import com.agree.bean.DETAIL_T_MALL_SKU;
import com.agree.bean.T_MALL_SKU;

import java.util.List;

public interface ItemMapper {

    public DETAIL_T_MALL_SKU select_detail_sku(int sku_id);
    public List<T_MALL_SKU> select_list_sku_by_spu(int spu_id);
}
