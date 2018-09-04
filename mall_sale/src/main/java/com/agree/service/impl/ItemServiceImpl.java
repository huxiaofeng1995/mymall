package com.agree.service.impl;

import com.agree.bean.DETAIL_T_MALL_SKU;
import com.agree.bean.T_MALL_SKU;
import com.agree.mapper.ItemMapper;
import com.agree.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<T_MALL_SKU> get_list_sku_by_spu(int spu_id) {
        return itemMapper.select_list_sku_by_spu(spu_id);
    }

    @Override
    public DETAIL_T_MALL_SKU get_sku_detail(int sku_id) {

        return itemMapper.select_detail_sku(sku_id);
    }
}
