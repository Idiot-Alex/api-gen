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
  console.log(params)
  return axios.get('/w/api-log/list', {
    params: params
  })
}