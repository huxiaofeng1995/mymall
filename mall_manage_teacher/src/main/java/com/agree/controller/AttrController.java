package com.agree.controller;

import com.agree.bean.MODEL_T_MALL_ATTR;
import com.agree.bean.OBJECT_T_MALL_ATTR;
import com.agree.bean.T_MALL_PRODUCT;
import com.agree.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AttrController {

    @Autowired
    private AttrService attrService;

    @RequestMapping("get_attr_list")
    public String get_attr_list(int flbh2, ModelMap map){
        List<OBJECT_T_MALL_ATTR> list_attr = new ArrayList<OBJECT_T_MALL_ATTR>();
        map.put("flbh2", flbh2);
        list_attr = attrService.get_attr_list(flbh2);
        map.put("list_attr", list_attr);
        return "attrListInner";
    }

    @RequestMapping("goto_attr_add")
    public String goto_spu_add(int flbh2,ModelMap modelMap) {
        modelMap.put("flbh2",flbh2);
        return "attrAdd";
    }

    @RequestMapping("attr_add")
    public RedirectView attr_add(int flbh2, MODEL_T_MALL_ATTR list_attr) {
        attrService.sava_attr(flbh2, list_attr);
        RedirectView rv = new RedirectView("goto_attr_add.do",true,false,true);
        rv.addStaticAttribute("flbh2",flbh2);
        return rv;
    }
}
