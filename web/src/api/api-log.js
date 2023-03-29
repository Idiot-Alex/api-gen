import axios from './config.js'

/**
 * api log list
 * @param {
 *  pageNo: number
 *  pageSize: number
 * } params 
 * @returns 
 */
export function list(params) {
  return axios.get('/w/api-log/list', {
    params: params
  })
}

// delete by id
export function del(id) {
  return axios.post(`/w/api-log/delete/${id}`)
}
