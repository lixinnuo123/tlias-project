import request from '@/utils/request';
//查询
export const queryPageApi=(name,degree,clazzId,page,pageSize)=> request.get(`/students?name=${name}&degree=${degree}&clazzId=${clazzId}&page=${page}&pageSize=${pageSize}`);

//新增

export const addApi=(stu)=> request.post('/students',stu)

//根据ID查询学生数据

export const queryByIdApi=(id)=> request.get(`/students/${id}`)
//修改

export const updateApi=(stu)=> request.put(`/students`,stu)

//删除
export const deleteByIdApi=(ids)=> request.delete(`/students/${ids}`)
