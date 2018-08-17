package com.agree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SkuController {

    @RequestMapping("goto_sku_add")
    public String goto_sku_add(@RequestParam("flbh1") int flbh1,@RequestParam("flbh2") int flbh2, ModelMap map){
        map.put("flbh1",flbh1);
        map.put("flbh2",flbh2);
        return "skuAdd";
    }
}
