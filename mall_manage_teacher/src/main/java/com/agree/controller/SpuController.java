package com.agree.controller;

import com.agree.bean.T_MALL_PRODUCT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpuController {

    @RequestMapping("goto_spu")
    public String goto_spu() {
        return "spu";
    }
    @RequestMapping("goto_spu_add")
    public String goto_spu_add(ModelMap map, T_MALL_PRODUCT spu) {

        map.put("spu", spu);
        return "spuAdd";
    }

    @RequestMapping("spu_add")
    public String spu_add() {
        return "redirect:/goto_spu_add.do";
    }
}
