package com.agree.service.impl;

import com.agree.bean.T_MALL_PRODUCT;
import com.agree.mapper.SpuMapper;
import com.agree.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService{

    @Autowired
    private SpuMapper spuMapper;

    @Override
    public void sava_spu(List<String> imgs, T_MALL_PRODUCT spu) {
        spu.setShp_tp(imgs.get(0));
        spuMapper.insert_spu(spu);
    }
}
