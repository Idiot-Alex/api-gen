
import apiReqCtx from './request-config.js'

/**
 * upload data
 * @param {JSON} data 
 * @returns APIResponse
 */
export async function upload(data) {
  return (await apiReqCtx()).post('/api/upload', {
    data: data
  })
}