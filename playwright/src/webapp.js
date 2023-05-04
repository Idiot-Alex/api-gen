import { chromium } from 'playwright-chromium'
import { health, loadClientConfig, upload } from './api/api.js'
import { execFunc, toast } from './notify.js'
import iconv from 'iconv-lite'

const app = async() => {
  const browser = await chromium.launch({
    args: [
      '--remote-debugging-port=9222',
    ],
    headless: false,
    cacheEnabled: false,
  });
}

const cdp = async() => {
  const browser = await chromium.connectOverCDP('http://127.0.0.1:9222/');
  
  const page = await browser.newPage()
  await page.setExtraHTTPHeaders({
    'Cache-Control': 'no-cache',
  })
  
  await page.goto("https://idiot-alex.github.io/api-gen/")

  // 加载配置
  const res = await initConfig(page)

  uploadConfig(browser, res.data || [])
}

/**
 * 初始化配置信息
 * @param {*} page 
 */
const initConfig = async (page) => {
  // 弹出提示信息框
  const func = async () => {
    await page.evaluate(() => {
      Swal.fire({
        allowOutsideClick: false,
        title: '初始化配置',
        html: '这花不了多少时间，请稍等...',
        didOpen: () => {
          Swal.showLoading()
        }
      })
    })
    return new Promise((resolve) => {
      setTimeout(async() => {
        // 请求接口
        try {
          let res = await health()
          if (!res.ok()) {
            toast(page, 'error', '初始化服务端配置信息失败')
            return
          }
          // 读取配置
          res = await loadClientConfig()
          if (!res.ok()) {
            toast(page, 'error', '初始化服务端配置信息失败')
            return
          }
          toast(page, 'success', '初始化服务端配置信息成功')
          // 处理数据
          const jsonData = await res.json()
          resolve(jsonData)
        } catch(err) {
          toast(page, 'error', '初始化服务端配置信息失败')
          resolve({})
        }
      }, 1500)
    })
  }
  const res = await execFunc(page, func)
  return Promise.resolve(res)
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
          console.log('process response text: ', iconv.decode(Buffer.from(text, 'latin1'), 'iso-8859-1'))
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
  console.log('======data: ', JSON.stringify(data))
  return data
}

const processDataAndConfigs = (data, configs) => {
  if (!data) {
    return false
  }
  // 遍历 configs
  configs.forEach(config => {
    let dataValue
    // process paramType
    switch (config.paramType) {
      case 'object':
      case 'array':
        dataValue = JSON.parse(config.paramValue)
        break;
      case 'string':
      default:
        dataValue = config.paramValue
        break;
    }
    // client_exclude_urlxxx
    if (config.paramKey.indexOf('client_exclude_url') !== -1) {
      if (typeof dataValue === 'string') {
        return data.url.indexOf(config.paramValue) === -1
      } else if (Array.isArray(dataValue)) {
        const arr = dataValue.filter(val => data.url.indexOf(val) !== -1)
        return arr.length === 0
      }
      return true
    }
  })
  return true
}

const uploadConfig = (browser, configs) => {
  browser.contexts().forEach(browserContext => {
    browserContext.on('requestfinished', async(request) => {
      const data = await buildData(request)

      // 处理 data 和 configs
      if (processDataAndConfigs(data, configs)) {
         upload(data).then(async res => {
          console.log('-----url: ', data.url, await res.text())
        }).catch(async err => {
          console.log('--requestfinished error: ', err.message)
        })
      }
    })
    browserContext.on('requestfailed', async(request) => {
      const data = await buildData(request)

      // 处理 data 和 configs
      if (processDataAndConfigs(data, configs)) {
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
  await cdp()
}

export { start }