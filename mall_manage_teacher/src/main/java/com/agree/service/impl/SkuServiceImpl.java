package com.agree.service.impl;

import com.agree.bean.T_MALL_PRODUCT;
import com.agree.bean.T_MALL_PRODUCT_SKU_INFO;
import com.agree.bean.T_MALL_SKU;
import com.agree.bean.T_MALL_SKU_ATTR_VALUE;
import com.agree.mapper.SkuMapper;
import com.agree.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuServiceImpl implements SkuService{

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public void save_sku(List<T_MALL_SKU_ATTR_VALUE> list_attr, T_MALL_SKU sku, T_MALL_PRODUCT spu, T_MALL_PRODUCT_SKU_INFO sku_info) {
        sku.setShp_id(spu.getId());
        skuMapper.insert_sku(sku);

        // 根据sku主键批量保存属性关联表
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("shp_id", spu.getId());
        map.put("sku_id", sku.getId());
        map.put("list_av", list_attr);
        skuMapper.insert_sku_attr_value(map);
        if(sku_info.getShp_ys()!= 0 && sku_info.getShp_bb()!= 0){
            map.put("sku_info",sku_info);
            skuMapper.insert_sku_info(map);
        }
    }
}
