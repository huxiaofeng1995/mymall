package com.agree.service.impl;

import com.agree.bean.T_MALL_PRODUCT;
import com.agree.mapper.SpuMapper;
import com.agree.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements SpuService{

    @Autowired
    private SpuMapper spuMapper;

    @Override
    public void sava_spu(List<String> imgs, T_MALL_PRODUCT spu) {
        //插入spu信息
        spu.setShp_tp(imgs.get(0));//默认商品显示的主图片/封面
        spuMapper.insert_spu(spu);

        //根据主键，批量插入spu图片
        Map<String, Object> map = new HashMap<>();
        map.put("shp_id", spu.getId());
        map.put("imgs", imgs);
        //spuMapper.insert_imgs(spu.getId(), imgs);
        //遇到多参数传递时，最好封装成map，这样方便在mapper.xml中获取
        spuMapper.insert_imgs(map);
    }

    @Override
    public List<T_MALL_PRODUCT> get_spu_list(int pp_id, int flbh2) {
        return spuMapper.select_spu_list(pp_id, flbh2);
    }
}
