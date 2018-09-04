package com.agree.service;

import com.agree.bean.OBJECT_T_MALL_PRODUCT;
import com.agree.bean.T_MALL_PRODUCT;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SpuService {
    public void sava_spu(List<String> imgs, OBJECT_T_MALL_PRODUCT spu);

    public void sava_spu2(List<String> imgs, T_MALL_PRODUCT spu);

    public List<T_MALL_PRODUCT> get_spu_list(int pp_id, int flbh2);
}
