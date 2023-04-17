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
  return axios.post('/w/api-log/list', {
    data: params
  })
}

// delete by id
export function del(id) {
  return axios.post(`/w/api-log/delete/${id}`)
}

// statistics
export function statistics() {
  return axios.post('/w/api-log/statistics')
}
