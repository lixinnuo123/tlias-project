package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Dept;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
    * 查询所有部门数据
    * */

    //方式一：手动封装
/*    @Results({
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })*/

    //方式二：起别名
   /* @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc ;")
    List<Dept> findAll() ;*/

    //方式三：在application.yml中配置  开启驼峰命名

    @Select("select id, name, create_time, update_time from dept order by update_time desc ;")
    List<Dept> findAll() ;

    /*
    * 根据ID删除部门
    * */
    @Delete("delete from dept where id=#{id}")  //最终会替换为 ？，生成预编译的SQL语句
    void deleteById(Integer id);

    /*
    * 新增部门
    * */
    @Insert("insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")//values 中的是属性名，应采用驼峰命名法
    void insert(Dept dept);

    /*
    * 根据ID查询部门
    * */
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getById(Integer id);

    /*
     * 更新修改部门
     * */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
