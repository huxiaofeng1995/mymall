package com.agree.controller;

import com.agree.bean.*;
import com.agree.service.AttrService;
import com.agree.service.ListService;
import com.agree.util.JedisUtils;
import com.agree.util.MyCacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ListController {

    @Autowired
    private ListService listService;

    @Autowired
    private AttrService attrService;

    @RequestMapping(value = "/get_list_by_attr")
    public String get_list_by_attr(MODEL_T_MALL_SKU_ATTR_VALUE model,int flbh2,Map map){
        List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();
        List<String> list_id = new ArrayList<>();
        //构造key
        for(T_MALL_SKU_ATTR_VALUE attr : model.getList_attr()){
            String id = "attr_" + flbh2 + "_" + attr.getShxm_id()+"_"+attr.getShxzh_id();
            list_id.add(id);
        }
        String[] key = new String[list_id.size()];
        list_id.toArray(key);
        //从缓存中获取
        list_sku = MyCacheUtil.getListByAttr(key);

        if(list_sku == null || list_sku.size() < 1) {
            list_sku = listService.get_sku_list_by_attr(model.getList_attr(), flbh2);
            //存入缓存的数据
            List<T_MALL_SKU_ATTR_VALUE> list_attr = new ArrayList<>();
            List<OBJECT_T_MALL_SKU> list = new ArrayList<>();
            for(T_MALL_SKU_ATTR_VALUE attr : model.getList_attr()){
                String akey = "attr_" + flbh2 + "_" + attr.getShxm_id()+"_"+attr.getShxzh_id();
                if(!JedisUtils.existsKey(akey)){
                    list_attr.add(attr);
                    list = listService.get_sku_list_by_attr(list_attr,flbh2);
                    list_attr.remove(attr);
                    MyCacheUtil.setListByAttr(akey,list);
                }
            }
        }
        map.put("list_sku",list_sku);
        map.put("count",list_sku.size());
        return "skuList";
    }

    @RequestMapping(value="/goto_search_class")
    public String goto_list(int flbh2,Map map){
        map.put("flbh2",flbh2);
        MODEL_CLASSNAME classname = listService.get_classname(flbh2);
        map.put("classname", classname);
        List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(flbh2);
        map.put("list_attr",list_attr);
        List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();
        //从缓存数据库中查
        String key = "flbh2_" + flbh2;
        //list_sku = JedisUtils.getList(key,OBJECT_T_MALL_SKU.class);
        list_sku = MyCacheUtil.getList(key,OBJECT_T_MALL_SKU.class);
        //缓存中找不到，则去数据库中找
        if(list_sku == null || list_sku.size() < 1) {
            list_sku = listService.get_sku_list(flbh2);
            //存入缓存数据库，下次再找时直接从缓存取出
            //JedisUtils.setList(key, list_sku);
            MyCacheUtil.setList(key, list_sku);
        }
        map.put("list_sku",list_sku);
        map.put("count",list_sku.size());
        return "list";
    }
}
