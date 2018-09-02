package com.agree.service;

import com.agree.bean.OBJECT_T_MALL_SKU;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ListService {

    public List<OBJECT_T_MALL_SKU> get_sku_list(int flbh2);
}
