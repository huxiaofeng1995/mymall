package com.agree.controller;

import com.agree.bean.DETAIL_T_MALL_SKU;
import com.agree.bean.T_MALL_SKU;
import com.agree.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/goto_sku_detail")
    public String goto_sku_detail(int sku_id, int spu_id, Map map){
        DETAIL_T_MALL_SKU detail_sku = itemService.get_sku_detail(sku_id);
        List<T_MALL_SKU> list_sku = itemService.get_list_sku_by_spu(spu_id);
        map.put("detail_sku",detail_sku);
        map.put("list_sku",list_sku);
        return "skuDetail";
    }
}
