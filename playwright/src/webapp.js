import { chromium } from "playwright-chromium"
import { upload } from "./api/api.js"
// import { notifyError, notifyApiResponseError } from "./notify.js"

const app = async() => {
  const browser = await chromium.launch({
    args: ["--remote-debugging-port=9222"],
    headless: false,
  });
}

const cdp = async() => {
  const browser = await chromium.connectOverCDP('http://127.0.0.1:9222/');
  
  const page = await browser.newPage();

  browser.contexts().forEach(browserContext => {
    browserContext.on('requestfinished', async(request) => {
      const data = await buildData(request)
      // console.log(">>" + JSON.stringify(data))
      if (data) {
        upload(data).then(async res => {
          console.log('-----', await res.text())
        }).catch(err => {
          console.log('-----', err)
          // notifyApiResponseError(err.message)
        })
      }
    })
    browserContext.on('requestfailed', async(request) => {
      const data = await buildData(request)
      // console.log("<<" + JSON.stringify(data))
      if (data) {
        upload(data).then(res => {
          console.log('-----', res)
        }).catch(err => {
          console.log('-----', err)
          // notifyApiResponseError(err.message)
        })
      }
    })
  })
  
  await page.goto("https://www.google.com/")

}

const buildData = async(request) => {
  const response = await request.response()
  if (!response) {
    return null
  }

  const size = await request.sizes()

  let text = ''

  if (response.ok() && response.status() !== 302) {
    const contentType = response.headers()['content-type'];
    if (contentType && (contentType.includes('text/') || contentType === 'application/json')) {
        try {
          text = await response.text();
        } catch (error) {
          console.log('error: ', contentType, error)
          // notifyError(error.message)
        }
    }
  }

  const data = {
    url: request.url(),
    method: request.method(),
    request: {
      resourceType: request.resourceType(),
      headers: request.headers(),
      postData: request.postData(),
      failed: false,
      errorText: request.failure()?.errorText,
      requestBodySize: size.requestBodySize,
      requestHeadersSize: size.requestHeadersSize
    },
    response: {
      headers: response.headers(),
      text: text,
      status: response.status(),
      statusText: response.statusText(),
      responseBodySize: size.responseBodySize,
      responseHeadersSize: size.responseHeadersSize
    }
  }
  return data
}

async function start() {
  await app()
  await cdp()
}

export { start }