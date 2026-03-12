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
import java.util.Arrays;
import java.util.List;

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

    /*
    * 删除员工
    * */

    /*//方式一：在Controller方法中通过数组来接收
    @DeleteMapping
    public Result delete(Integer[] ids){
        log.info("删除员工：{}", Arrays.toString(ids));//看到数组中的元素
        return Result.success();
    }*/

    //方式二：在Controller方法中通过集合接收（@RequestParam不能省略）
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    /*
    * 修改员工（查询回显）
    * */
    //根据ID查询员工信息（基本+工作经历）
    @GetMapping("/{id}")
    public Result geyInfo(@PathVariable Integer id){
        log.info("根据ID查询员工信息：{}",id);
        Emp emp =empService.getInfo(id);
        return Result.success(emp);
    }

    //修改数据
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
