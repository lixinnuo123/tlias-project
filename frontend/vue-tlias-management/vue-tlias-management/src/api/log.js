import request from '@/utils/request';

//分页查询日志数据
export const queryPageApi = (page, pageSize) =>{
  return request({
    url: '/log/page',
    method: 'get',
    params: {
      page,
      pageSize
    }
  })
};