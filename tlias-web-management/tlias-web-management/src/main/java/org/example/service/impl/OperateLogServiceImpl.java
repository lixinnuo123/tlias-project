package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;
import org.example.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }

        PageHelper.startPage(page, pageSize);
        List<OperateLog> list = operateLogMapper.page();
        Page<OperateLog> p = (Page<OperateLog>) list;

        return new PageResult<>(p.getTotal(), p.getResult());
    }

}
