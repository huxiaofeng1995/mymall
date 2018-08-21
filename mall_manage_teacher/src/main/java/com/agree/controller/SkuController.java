package com.agree.controller;

import com.agree.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.agree.bean.OBJECT_T_MALL_ATTR;
import com.agree.bean.T_MALL_PRODUCT;
import com.agree.bean.T_MALL_SKU;
import com.agree.mapper.AttrMapper;
import com.agree.service.AttrService;
import com.agree.service.SkuService;
import com.agree.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SkuController {

    @Autowired
    private AttrService attrService;

    @Autowired
    private SkuService skuService;

    @RequestMapping("goto_sku_add")
    public String goto_sku_add(@RequestParam("flbh1") int flbh1,@RequestParam("flbh2") int flbh2, ModelMap map){
        List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(flbh2);
        map.put("flbh1",flbh1);
        map.put("flbh2",flbh2);
        map.put("list_attr",list_attr);
        return "skuAdd";
    }

    @RequestMapping("save_sku")
    public ModelAndView save_sku(MODEL_T_MALL_SKU_ATTR_VALUE list_attr, T_MALL_SKU sku, T_MALL_PRODUCT spu) {

        skuService.save_sku(list_attr.getList_attr(), sku, spu);
        //ModelAndView mv = new ModelAndView("redirect:/goto_sku_add.do");
        //mv.addObject("flbh1",spu.getFlbh1());
        //mv.addObject("flbh2",spu.getFlbh2());
        ModelAndView mv = new ModelAndView("redirect:/index.do");
        mv.addObject("url","goto_sku_add.do?flbh1="+spu.getFlbh1()+"&flbh2="+spu.getFlbh2());
        mv.addObject("title", "添加库存");
        return mv;
    }
}
