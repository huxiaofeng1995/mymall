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
    public String spu_add(@RequestParam("files") MultipartFile[] files, T_MALL_PRODUCT spu) {
        //上传图片
        List<String> imgs = FileUploadUtil.upload_img(files);
        //保存商品
        spuService.sava_spu(imgs, spu);
        return "redirect:/goto_spu_add.do";
    }
}
