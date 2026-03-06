package org.example.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Emp;

import java.util.List;

/*
* 员工信息
* */
@Mapper
public interface EmpMapper {
    //------------------------------------原始分页查询的实现方式--------------------------------------------
   /* *//*
    * 查询总记录数
    * *//*
    @Select("select count(*)from emp e left outer join dept d on e.dept_id=d.id ")
    public Long count();

    *//*
    * 查询结果列表
    * *//*
    @Select("select e.*,d.name deptName from emp e left outer join dept d on e.dept_id=d.id " +
            "order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);*/

    //-----------------------------------------PageHelper分页插件方式------------------------------------------------

    @Select("select e.*,d.name deptName from emp e left outer join dept d on e.dept_id=d.id order by e.update_time desc ")
    public List<Emp> list();

}
