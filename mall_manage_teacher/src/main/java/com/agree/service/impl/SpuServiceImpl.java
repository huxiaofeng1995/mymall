package com.agree.service.impl;

import com.agree.bean.OBJECT_T_MALL_PRODUCT;
import com.agree.bean.T_MALL_PRODUCT;
import com.agree.bean.T_MALL_PRODUCT_COLOR;
import com.agree.bean.T_MALL_PRODUCT_VERSION;
import com.agree.mapper.SpuMapper;
import com.agree.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements SpuService{

    @Autowired
    private SpuMapper spuMapper;

    @Override
    public void sava_spu(List<String> imgs, OBJECT_T_MALL_PRODUCT spu) {
        //插入spu信息
        spu.setShp_tp(imgs.get(0));//默认商品显示的主图片/封面
        spuMapper.insert_spu(spu);

        //根据主键，批量插入spu图片
        Map<String, Object> map = new HashMap<>();
        map.put("shp_id", spu.getId());
        if(imgs.size()>0) {
            map.put("imgs", imgs);
            //spuMapper.insert_imgs(spu.getId(), imgs);
            //遇到多参数传递时，最好封装成map，这样方便在mapper.xml中获取
            spuMapper.insert_imgs(map);
        }
        List<T_MALL_PRODUCT_COLOR> list_color = spu.getList_color();
        list_color.removeAll(Collections.singleton(""));//去空字符串值
        if(list_color.size()>0) {
            map.put("list_color", list_color);
            spuMapper.insert_colors(map);
        }
        List<T_MALL_PRODUCT_VERSION> list_version = spu.getList_version();
        list_version.removeAll(Collections.singleton(""));
        if(list_version.size()>0) {
            map.put("list_version", list_version);
            spuMapper.insert_versions(map);
        }
    }

    @Override
    public void sava_spu2(List<String> imgs, T_MALL_PRODUCT spu) {
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

    @Override
    public OBJECT_T_MALL_PRODUCT get_spu_sale_attr(int spu_id) {
        List<T_MALL_PRODUCT_COLOR> list_color = spuMapper.select_color_list(spu_id);
        List<T_MALL_PRODUCT_VERSION> list_version = spuMapper.select_version_list(spu_id);
        OBJECT_T_MALL_PRODUCT spu = new OBJECT_T_MALL_PRODUCT();
        spu.setList_color(list_color);
        spu.setList_version(list_version);
        return spu;
    }


}
