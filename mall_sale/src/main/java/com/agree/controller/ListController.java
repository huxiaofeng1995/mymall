package com.agree.controller;

import com.agree.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.agree.bean.OBJECT_T_MALL_SKU;
import com.agree.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class ListController {

    @Autowired
    private ListService listService;

    @RequestMapping(value = "/get_list_by_attr")
    public String get_list_by_attr(MODEL_T_MALL_SKU_ATTR_VALUE model,int flbh2,Map map){
        List<OBJECT_T_MALL_SKU> list_sku = listService.get_sku_list_by_attr(model.getList_attr(),flbh2);
        map.put("list_sku",list_sku);
        return "skuList";
    }
}
