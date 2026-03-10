package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    /*
    * 文件上传
    * */
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file){
        log.info("接受参数：{},{},{}",name,age,file);
        return Result.success();
    }
}
