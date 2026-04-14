import request from '@/utils/request';

//查询员工职位统计数据
export const queryEmpReportDataApi = () => request.get('/report/empJobData');

//查询员工性别统计数据
export const queryEmpReportGenderDataApi = () => request.get('/report/empGenderData');

//查询学员班级人数统计数据
export const queryStudentReportDataApi = () => request.get('/report/studentCountData');

//查询学员学历统计数据
export const queryStudentReportDegreeDataApi = () => request.get('/report/studentDegreeData');
