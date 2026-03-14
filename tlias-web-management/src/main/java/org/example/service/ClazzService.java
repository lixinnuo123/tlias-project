package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryPaeam;
import org.example.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /*
    * 分页查询
    * */
    PageResult<Clazz> page(ClazzQueryPaeam clazzQueryPaeam);

    /*
    * 删除班级
    * */
    void delete(Integer id);

    /*
    * 添加班级
    * */
    void save(Clazz clazz);

    /*
    * 根据ID查询班级
    * */
    Clazz get(Integer id);

    /*
    * 修改班级
    * */
    void update(Clazz clazz);

    /*
    * 查询所有班级
    * */
    List<Clazz> list();
}
