import { request } from 'playwright-chromium'

const apiReqCtx = async () => {
  return await request.newContext({
    baseURL: 'http://127.0.0.1:8080/',
    timeout: 1000 * 5
  });
}

export default apiReqCtx