package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.EmpLog;

import java.util.List;

@Mapper
public interface EmpLogMapper {

    @Insert("insert into emp_log (operate_time, info) values (#{operateTime}, #{info})")
    public void insert(EmpLog empLog);

    /**
     * 分页查询操作日志（包含操作人信息）
     */
    List<EmpLog> list();

    /**
     * 查询总记录数
     */
    Long count();

}
