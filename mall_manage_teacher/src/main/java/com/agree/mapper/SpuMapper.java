package com.agree.mapper;

import com.agree.bean.T_MALL_PRODUCT;

import java.util.List;
import java.util.Map;

public interface SpuMapper {
    public void insert_spu(T_MALL_PRODUCT product);

    public void insert_imgs(Map<String, Object> map);
}
