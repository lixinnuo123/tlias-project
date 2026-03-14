package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzMapper;
import org.example.pojo.*;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceimpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /*
    * 分页查询班级信息
    * */
    @Override
    public PageResult<Clazz> page(ClazzQueryPaeam clazzQueryPaeam) {
        //1.设置分页参数（pagehelper）
        PageHelper.startPage(clazzQueryPaeam.getPage(),clazzQueryPaeam.getPageSize());
        //2.执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryPaeam);
        //3.解析查询结果，并封装
        Page<Clazz> p = (Page<Clazz>) clazzList;//Page为List的子类，所以应该强转
        return new PageResult<>(p.getTotal(),p.getResult());//Page提供的方法，p.getTotal来获取总记录数
    }

    /*
    * 根据ID删除班级
    * */
   @Override
   public void delete(Integer id) {
        clazzMapper.deleteByIds(id);

    }

    /*
    * 添加班级
    * */
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /*
    * 根据ID查询班级
    * */
    @Override
    public Clazz get(Integer id) {
        return clazzMapper.getById(id);
    }

    /*
    * 修改班级
    * */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    /*
    * 查询所有班级
    * */
    @Override
    public List<Clazz> list() {
        return clazzMapper.findAll();
    }


}
