package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {
    /*
    * 分页查询
    * */
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    /*
    * 新增员工信息
    * */
    void save(Emp emp);

    /*
    分页查询的方法
    * */
/*    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);*/
}
