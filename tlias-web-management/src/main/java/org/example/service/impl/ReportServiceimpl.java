package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.JobOption;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceimpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        //调用Mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();//map：pos=教研主管，num=1
        //组装结果并返回
        List<Object> joblist = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> datalist = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(joblist,datalist);
    }

    @Override
    public List<Map<String, Object>> getGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentCountData() {
        return studentMapper.countStudentData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }
}
