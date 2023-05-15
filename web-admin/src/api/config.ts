import axios, { AxiosResponse } from 'axios'
import { MyAxiosResponse } from '~/utils/types'
import qs from 'qs'
import { ElNotification } from 'element-plus'

const service = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000
})

service.interceptors.request.use(
  (config: any) => {
    // 在请求发送前可以做一些处理
    // 比如设置请求头、修改请求参数等操作
    const contentType: any = config.headers['Content-Type']
    if (!contentType || contentType.toLowerCase().includes('www-form-urlencoded')) {
      config.data = qs.stringify(config.data)
    }
    return config
  },
  (error: any) => {
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
    const msg = `<strong>${error?.message}</strong></br><span>${error?.config?.baseURL}${error?.config?.url}</span>`
    ElNotification.error({
      title: error.name || 'Error',
      dangerouslyUseHTMLString: true,
      message: msg,
      offset: 100,
    })
    return Promise.reject(error)
  }
)

export default service