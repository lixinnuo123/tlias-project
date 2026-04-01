import request from '@/utils/request';

//查询全部的班级数据
export const queryAllApi=()=> request.get('/clazzs/list');

//新增班级数据
export const addApi=(clazz)=> request.post('/clazzs',clazz);  

//根据ID查询班级数据
export const queryByIdApi=(id)=> request.get(`/clazzs/${id}`);

//修改

export const updateApi=(clazz)=> request.put('/clazzs',clazz);  

//删除
export const deleteByIdApi=(id)=> request.delete(`/clazzs/${id}`);