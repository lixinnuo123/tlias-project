package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.pojo.EmpLog;
import org.example.mapper.EmpLogMapper;
import org.example.pojo.PageResult;
import org.example.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
/*
* 分页查询操作日志
* */
@Override
public PageResult<EmpLog> page(Integer page, Integer pageSize) {
    // 设置分页参数
    PageHelper.startPage(page, pageSize);

    // 执行查询
    List<EmpLog> logList = empLogMapper.list();

    // 获取分页结果
    Page<EmpLog> p = (Page<EmpLog>) logList;

    return new PageResult<>(p.getTotal(), p.getResult());
}
}

