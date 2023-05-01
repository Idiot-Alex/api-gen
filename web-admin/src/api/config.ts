import axios, { AxiosResponse } from 'axios'
import { MyAxiosResponse } from '~/utils/types'
import qs from 'qs'

const service = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000
})

service.interceptors.request.use(
  config => {
    // 在请求发送前可以做一些处理
    // 比如设置请求头、修改请求参数等操作
    config.data = qs.stringify(config.data)
    return config
  },
  error => {
    // 当请求发生错误时可以在这里做一些处理
    return Promise.reject(error)
  }
)

service.interceptors.response.use(
  (response: AxiosResponse<MyAxiosResponse>) => {
    // 在响应数据返回前可以做一些处理
    // 比如统一处理响应数据、返回错误信息等操作
    return response.data
  },
  error => {
    // 当响应发生错误时可以在这里做一些处理
    console.error(error)
    return Promise.reject(error)
  }
)

export default service