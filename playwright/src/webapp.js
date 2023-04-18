import { chromium } from 'playwright-chromium'
import { upload } from './api/api.js'
// import { notifyError, notifyApiResponseError } from "./notify.js"

const execPageScript = async (page, scriptFile) => {
  await page.evaluate(async () => {
    if (('Swal' in window) === false) {
      await page.addScriptTag({path: scriptFile})
    }
    Swal.fire({
      title: '操作成功',
      icon: 'success',
      timer: 3000,
      showConfirmButton: false
    })
  })
}

const app = async() => {
  const browser = await chromium.launch({
    args: [
      "--remote-debugging-port=9222",
    ],
    headless: false,
  });
}

const cdp = async() => {
  // 获取 SweetAlert2 文件的绝对路径
  const sweetAlertFile = 'src/lib/sweetalert2.all.min.js'

  const browser = await chromium.connectOverCDP('http://127.0.0.1:9222/');

  browser.contexts().forEach(async (browserContext) => {
    // 注入 SweetAlert2 文件
    // browserContext.pages().forEach(async (page1) => {
    //   console.log('....')
    //   // 监听 frame.navigated 事件，检查每个 frame 是否已经发生导航
    //   page1.on('frameattached', async (frame) => {
    //     await frame.addScriptTag({ path: sweetAlertFile})
    //   })
    //   page1.on('framenavigated', async (frame) => {
    //     await frame.addScriptTag({ path: sweetAlertFile})
    //   })
    // })

    browserContext.on('page', async (pg) => {
      console.log('page ', pg.url())
      // 监听 frame.navigated 事件，检查每个 frame 是否已经发生导航
      pg.on('frameattached', async (frame) => {
        await frame.addScriptTag({ path: sweetAlertFile})
      })
      pg.on('framenavigated', async (frame) => {
        await frame.addScriptTag({ path: sweetAlertFile})
      })
    })

    browserContext.on('requestfinished', async(request) => {
      const data = await buildData(request)
      // console.log(">>" + JSON.stringify(data))
      if (data) {
        upload(data).then(async res => {
          console.log('-----', await res.text())
        }).catch(err => {
          // console.log('-----', err)
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
          // console.log('-----', err)
          // notifyApiResponseError(err.message)
        })
      }
    })
  })
  
  const page = await browser.newPage()
  await page.goto("https://idiot-alex.github.io/api-gen/")

  if (!await page.evaluate(() => ('Swal' in window))) {
    await page.addScriptTag({path: sweetAlertFile})
  }
  // execPageScript(page, sweetAlertFile)
  await page.evaluate(() => {
    Swal.fire({
      title: '操作成功',
      icon: 'success',
      timer: 3000,
      showConfirmButton: false
    })
  })
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