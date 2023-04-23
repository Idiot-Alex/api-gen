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
export function list(params: Object): Promise<MyAxiosResponse> {
  return axios.post('/w/api-log/list', {
    ...params
  })
}

// delete by id
export function del(id: string): Promise<MyAxiosResponse> {
  return axios.post(`/w/api-log/delete/${id}`)
}

// statistics
export function statistics(): Promise<MyAxiosResponse> {
  return axios.post('/w/api-log/statistics')
}
