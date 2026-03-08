package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/*
* 员工管理
* */
@RequestMapping("/emps")
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //启动默认值 用@RequestParam   指定接受的日期格式 @DateTimeFormat(patter="...")
    /*@GetMapping
    public Result Page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        *//*if (page == null){
            page = 1;
        }*//*
        log.info("分页查询：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageResult<Emp> pageResult= empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageResult);
    }*/


    @GetMapping
    public Result Page(EmpQueryParam empQueryParam  ){
        log.info("分页查询：{},{},{},{},{},{}",empQueryParam);
        PageResult<Emp> pageResult= empService.page(empQueryParam);
        return Result.success(pageResult);
    }


    /*
    * 新增员工
    * */

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }
}
