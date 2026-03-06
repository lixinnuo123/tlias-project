package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.example.pojo.PageResult;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /*
    * 原始分页查询
    * */
   /* @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.调用mappper接口，查询总记录数
         Long total=empMapper.count();

        //2.调用mapper接口，查询结果列表
        //前端传递的参数和接收的参数不同
        Integer start=(page-1)*pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //3.封装结果  PageResult
        return new PageResult<Emp>(total,rows);
    }*/

    /*
    * PageHelper分页查询
    * */
    //page 页码
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.设置分页参数（pagehelper）
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<Emp> empList = empMapper.list();
        //3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) empList;//Page为List的子类，所以应该强转
        return new PageResult<Emp>(p.getTotal(),p.getResult());//Page提供的方法，p.getTotal来获取总记录数
    }
}
