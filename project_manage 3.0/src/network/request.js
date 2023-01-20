import axios from 'axios'
import { getToken } from '@/utils/auth'


axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

export function request(config) {
  const instance = new axios.create({
    // 请求头部信息
    //baseURL:'http://127.0.0.1:4523/m1/2178167-0-default', // Apifox
    baseURL:'http://127.0.0.1:8090',
    timeout:5000
  });

  // 请求拦截器
  instance.interceptors.request.use(config => {
    const isToken = (config.headers || {}).isToken === false
    if (getToken() && !isToken) {
      config.headers['token'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }  
    return config;
  }, err => {
    console.log(err)
  })

  // 响应拦截器
  instance.interceptors.response.use(res => {
    return res.data
  }, err => {
    console.log(err)
  })

  return instance(config)
}
