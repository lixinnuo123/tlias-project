package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
* 员工管理
* */
@RequestMapping("/emps")
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //启动默认值 用@RequestParam
    @GetMapping
    public Result Page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        /*if (page == null){
            page = 1;
        }*/
        log.info("分页查询：{},{}",page,pageSize);
        PageResult<Emp> pageResult= empService.page(page,pageSize);
        return Result.success(pageResult);
    }


}
