package org.example.service;

import org.example.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getGenderData();

    List<Map<String, Object>> getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
