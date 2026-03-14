package org.example.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryPaeam;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /*
    * 条件查询
    * */
    public List<Clazz> list(ClazzQueryPaeam clazzQueryPaeam);

    /*
    * 删除班级
    * */
    @Delete("delete from clazz where id=#{id}")
    void deleteByIds(Integer id);

    /*
    * 添加班级
    * */
    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    /*
    * 根据ID查询班级
    * */
    @Select("select id,name,room,begin_date,end_date,master_id,subject,create_time,update_time from clazz where id=#{id}")
    Clazz getById(Integer id);

    /*
    * 修改班级
    * */
    @Insert("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject},update_time=#{updateTime} where id=#{id}")
    void update(Clazz clazz);

    /*
    * 查询所有班级
    * */
    @Select("select id,name,room,begin_date,end_date,master_id,subject,create_time,update_time from clazz")
    List<Clazz> findAll();


}
