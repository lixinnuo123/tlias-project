package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryPaeam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /*
    * 分页查询
    * */
    @GetMapping
    public Result Page(ClazzQueryPaeam clazzQueryPaeam){
        log.info("分页查询：{},{},{},{},{}",clazzQueryPaeam);
        PageResult<Clazz> pageResult= clazzService.page(clazzQueryPaeam);
        return Result.success(pageResult);
    }

    /*
    * 删除班级
    * */
    @DeleteMapping("/{id}")
    public Result delete(Integer id){
        log.info("删除班级：{}",id);
        clazzService.delete(id);
        return Result.success();
    }

    /*
    * 添加班级
    * */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班级：{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /*
    * 根据ID查询班级
    * */
    @GetMapping("/{id}")
    public Result get(Integer id){
        log.info("查询班级：{}",id);
        Clazz clazz = clazzService.get(id);
        return Result.success(clazz);
    }

    /*
    * 修改班级
    * */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /*
    * 查询全部班级
    * */
    @GetMapping("/list")
    public Result list(){
        log.info("查询全部班级");
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }

}
