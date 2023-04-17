import axios from './config'
import { MyAxiosResponse } from '~/utils/types'

/**
 * api log list
 * @param {
 *  pageNo: number
 *  pageSize: number
 * } params 
 * @returns 
 */
export function list(params: Object) {
  return axios.post('/w/api-log/list', {
    data: params
  })
}

// delete by id
export function del(id: string) {
  return axios.post<MyAxiosResponse>(`/w/api-log/delete/${id}`)
}

// statistics
export function statistics() {
  return axios.post<MyAxiosResponse>('/w/api-log/statistics')
}
