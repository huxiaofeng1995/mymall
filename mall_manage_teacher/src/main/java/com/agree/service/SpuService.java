package com.agree.service;

import com.agree.bean.T_MALL_PRODUCT;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SpuService {
    public void sava_spu(List<String> imgs, T_MALL_PRODUCT spu);
}
