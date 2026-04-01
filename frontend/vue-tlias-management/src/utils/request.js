import axios from 'axios'

//创建axios实例对象
const request = axios.create({
  baseURL: '/api',//用于区分请求，也可以换成别的
  timeout: 600000
})

//axios的响应 response 拦截器
request.interceptors.response.use(
  (response) => { //成功回调
    return response.data
  },
  (error) => { //失败回调
    return Promise.reject(error)
  }
)

export default request