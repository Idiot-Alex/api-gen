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
  return axios.post('/w/api-log/list', {...params})
}

// delete by id
export function del(id: string): Promise<MyAxiosResponse> {
  return axios.post(`/w/api-log/delete/${id}`)
}

// delete by ids
export function delBatch(ids: any): Promise<MyAxiosResponse> {
  return axios.post('/w/api-log/delete-batch', 
    JSON.stringify(ids), 
    {
      headers: {
        'Content-Type': 'application/json'
      }
    })
}

// statistics
export function statistics(): Promise<MyAxiosResponse> {
  return axios.post('/w/api-log/statistics')
}
