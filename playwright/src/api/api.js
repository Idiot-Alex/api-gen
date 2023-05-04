
import apiReqCtx from './request-config.js'

// check health
export async function health() {
  return (await apiReqCtx()).post('/w/api/health')
}

/**
 * upload data
 * @param {JSON} data 
 * @returns APIResponse
 */
export async function upload(data) {
  return (await apiReqCtx()).post('/w/api/upload', {
    data: data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

// load config
export async function loadClientConfig() {
  return (await apiReqCtx()).post('/w/sys-config/load-client')
}