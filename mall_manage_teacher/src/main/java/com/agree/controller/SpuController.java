package com.agree.controller;

import com.agree.bean.T_MALL_PRODUCT;
import com.agree.service.SpuService;
import com.agree.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SpuController {

    @Autowired
    private SpuService spuService;

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
    public ModelAndView spu_add(@RequestParam("files") MultipartFile[] files, T_MALL_PRODUCT spu) {
        //上传图片
        List<String> imgs = FileUploadUtil.upload_img(files);

        //保存商品
        spuService.sava_spu(imgs, spu);
        ModelAndView mv = new ModelAndView("redirect:/goto_spu_add.do");
        //new RedirectView("/goto_spu_add.do").;
        //返回时下面这个参数将封装到请求参数里头
        //mv.addObject("spu",spu); 直接将spu放入model里并不能进行参数传递
        mv.addObject("flbh1",spu.getFlbh1());
        mv.addObject("flbh2",spu.getFlbh2());
        mv.addObject("pp_id",spu.getPp_id());
        return mv;
    }
}
