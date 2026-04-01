package org.example.mapper;


import org.apache.ibatis.annotations.*;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {


    public List<Student> list(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    @Insert("insert into student(name,no,gender,phone,degree,clazz_id,id_card,is_college,address,graduation_date) values (#{name},#{no},#{gender},#{phone},#{degree},#{clazzId},#{idCard},#{isCollege},#{address},#{graduationDate})")
    void insert(Student student);

    @Select("select * from student where id=#{id}")
    Student grtById(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);

    /*
     * 统计班级人数信息
     * */
    @MapKey("name")
    List<Map<String,Object>> countStudentData();

    /*
    * 统计学员学历信息
    * */
    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();
}

