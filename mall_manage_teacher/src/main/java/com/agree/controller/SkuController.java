package com.agree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SkuController {

    @RequestMapping("goto_sku_add")
    public String goto_sku_add(){

        return "skuAdd";
    }
}
