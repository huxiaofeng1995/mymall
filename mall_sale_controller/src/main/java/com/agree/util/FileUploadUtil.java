package com.agree.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUploadUtil {

    public static List<String> upload_img(MultipartFile[] files){
        String path = MyPropertyUtil.getProperty("myUpload.properties", "window_path");
        List<String> list = new ArrayList<String>();
        try {
            for (MultipartFile file : files) {
                if(!file.isEmpty()) {//非空
                    String originName = file.getOriginalFilename();
                    //名字去重
                    String filename = UUID.randomUUID().toString() + originName;
                    String uploadPath = path + File.separator + filename;
                    file.transferTo(new File(uploadPath));
                    list.add(filename);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;//返回的应该是文件名的集合，不包含路径名
    }
}
