package com.agree.controller;

import com.agree.bean.T_MALL_PRODUCT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AttrController {
    @RequestMapping("goto_attr_add")
    public String goto_spu_add() {

        return "attrAdd";
    }
}
