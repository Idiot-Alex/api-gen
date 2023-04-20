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
  return axios.post('/w/sys-config/list', {
    data: params
  })
}

// delete by id
export function del(id: string): Promise<MyAxiosResponse> {
  return axios.post(`/w/sys-config/delete/${id}`)
}

// save
export function save(params: Object): Promise<MyAxiosResponse> {
  return axios.post('/w/sys-config/save', {
    data: params
  })
}
