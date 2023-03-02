import { request } from 'playwright'

const apiReqCtx = async () => {
  return await request.newContext({
    baseURL: 'http://127.0.0.1:8080/',
    timeout: 1000 * 10
  });
}

export default apiReqCtx