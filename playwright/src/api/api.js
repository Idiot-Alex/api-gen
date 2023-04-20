
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
    data: data
  })
}

// load config
export async function loadClientConfig() {
  return (await apiReqCtx()).post('/w/config/load-client')
}