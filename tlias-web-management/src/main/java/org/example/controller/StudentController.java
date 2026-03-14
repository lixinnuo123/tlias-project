package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //条件分页查询
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询：{}",studentQueryParam);
        PageResult<Student> pageResult= studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /*
    * 批量删除学员信息
    * */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除：{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    /*
    * 添加学员信息
    * */
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加学员：{}",student);
        studentService.save(student);
        return Result.success();
    }

    /*
    * 根据ID查询学员信息
    * */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询学员信息：{}",id);
        Student student = studentService.geiById(id);
        return Result.success(student);
    }

    /*
    * 修改学员信息
    * */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学员信息：{}",student);
        studentService.update(student);
        return Result.success();
    }

    /*
    * 违纪处理
    * */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("违纪处理：{}",id);
        studentService.violation(id,score);
        return Result.success();
    }
}
