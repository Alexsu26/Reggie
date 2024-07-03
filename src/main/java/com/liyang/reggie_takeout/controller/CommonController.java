package com.liyang.reggie_takeout.controller;


import com.liyang.reggie_takeout.common.R;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 上传图片，文件类型需要是 MultipartFile，文件名需要与前端约定的一致，这里为file
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {

        // file其实是个临时文件，必须要转存到其他位置，否则连接关闭就找不到了
        String originalFilename = file.getOriginalFilename();   // abc.jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));  //.jpg
        // 使用UUID随机生成文件名
        String fileName = UUID.randomUUID().toString() + suffix;

        // 判断basePath是否存在
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }


        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {

        try {
            // 输入流获取
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            response.setContentType("image/jpeg");

            // 向浏览器输出流
            ServletOutputStream outputStream = response.getOutputStream();
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            // 关闭资源
            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
