import { chromium } from 'playwright-chromium'
import { health, loadConfig, upload } from './api/api.js'
import { execFunc, toast } from './notify.js'

const app = async() => {
  const browser = await chromium.launch({
    args: [
      "--remote-debugging-port=9222",
    ],
    headless: false,
  });
}

const cdp = async() => {
  const browser = await chromium.connectOverCDP('http://127.0.0.1:9222/');
  
  const page = await browser.newPage()
  
  await page.goto("https://idiot-alex.github.io/api-gen/")

  // 加载配置
  const configList = await initConfig(page)

  return Promise.resolve({ browser, configList })
}

/**
 * 初始化配置信息
 * @param {*} page 
 */
const initConfig = (page) => {
  let configList = []
  // 弹出提示信息框
  const func = async () => {
    page.evaluate(() => {
      Swal.fire({
        allowOutsideClick: false,
        title: '初始化配置',
        html: '这花不了多少时间，请稍等...',
        didOpen: () => {
          Swal.showLoading()
        }
      })
    }).then(() => {
      setTimeout(() => {
        // 请求接口
        health().then(() => {
          loadConfig().then(res => {
            toast(page, '初始化服务端配置信息成功')
            // 读取配置
            configList = res.data
          }).catch(err => {
            console.error('loadConfig error: ', err.message)
            toast(page, '初始化服务端配置信息失败')
          })
        }).catch(err => {
          console.error('health error: ', err.message)
          toast(page, '初始化服务端配置信息失败')
        })
      }, 1500)
    })
  }
  execFunc(page, func)
  return Promise.resolve(configList)
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
          console.log('process response error: ', contentType, error)
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

const uploadConfig = (browser) => {
  browser.contexts().forEach(browserContext => {
    browserContext.on('requestfinished', async(request) => {
      const data = await buildData(request)
      // console.log(">>" + JSON.stringify(data))
      if (data) {
        upload(data).then(async res => {
          console.log('-----', await res.text())
        }).catch(async err => {
          console.log('--requestfinished error: ', err.message)
        })
      }
    })
    browserContext.on('requestfailed', async(request) => {
      const data = await buildData(request)
      // console.log("<<" + JSON.stringify(data))
      if (data) {
        upload(data).then(res => {
          console.log('-----', res)
        }).catch(async err => {
          console.log(err.message)
          console.log('--requestfailed error: ', err.message)
        })
      }
    })
  })
}

async function start() {
  await app()
  cdp().then(({browser, configList}) => {
    console.log(configList)
    uploadConfig(browser)
  })
}

export { start }