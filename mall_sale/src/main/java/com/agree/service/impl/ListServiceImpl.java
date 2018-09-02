package com.agree.service.impl;

import com.agree.bean.OBJECT_T_MALL_SKU;
import com.agree.mapper.ListMapper;
import com.agree.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListServiceImpl implements ListService {
    @Autowired
    private ListMapper listMapper;
    @Override
    public List<OBJECT_T_MALL_SKU> get_sku_list(int flbh2) {
        return listMapper.select_list_sku(flbh2);
    }
}
