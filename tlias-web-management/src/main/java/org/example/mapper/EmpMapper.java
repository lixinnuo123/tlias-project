package org.example.mapper;


import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.time.LocalDate;
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

   /* @Select("select e.*,d.name deptName from emp e left outer join dept d on e.dept_id=d.id order by e.update_time desc ")*/
   /*public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);*/

    /*
    *条件查询员工信息
    * */
    public List<Emp> list(EmpQueryParam empQueryParam);

    /*
    * 新增员工基本信息
    * */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert(value = "insert into  emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /*
    * 批量删除员工基本信息（动态SQL）
    * */
    void deleteByIds(List<Integer> ids);



}
