package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.EmpExprMapper;
import org.example.pojo.EmpLog;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.example.pojo.EmpExpr;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;
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
    /*注意：
    *       1.定义的SQL语句结尾不能加分号；
    *       2.PageHelper仅仅能对紧跟在其后的第一个查询语句后进行分页处理
    * */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数（pagehelper）
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) empList;//Page为List的子类，所以应该强转
        return new PageResult<Emp>(p.getTotal(),p.getResult());//Page提供的方法，p.getTotal来获取总记录数
    }

    @Transactional(rollbackFor = {Exception.class})//事务管理
    @Override
    public void save(Emp emp) {
        try {
            //1.补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //2.保存员工的基本信息
            empMapper.insert(emp);

            //3.保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empId赋值
                exprList.forEach(empExpr ->{
                            empExpr.setEmpId(emp.getId());
                        });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志  无论成功还是失败，都记录日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工："+emp.getName());
            empLogService.insertLog(empLog);
        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
     //1.删除员工基本信息
        empMapper.deleteByIds(ids);
     //2.删除员工工作经历信息
        empExprMapper.deleteempExpr(ids);
    }
}
