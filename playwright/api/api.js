import axios from './axios-config.js'

export function upload(data) {
  return axios({
    url: '/api/upload',
    method: 'post',
    timeout: 0,
    data
  })
}