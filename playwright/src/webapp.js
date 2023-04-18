import { chromium } from 'playwright-chromium'
import { upload } from './api/api.js'
import { notifyError, notifyApiResponseError } from './notify.js'

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
  
  const page = await browser.newPage()

  browser.contexts().forEach(browserContext => {
    browserContext.on('requestfinished', async(request) => {
      const data = await buildData(request)
      // console.log(">>" + JSON.stringify(data))
      if (data) {
        upload(data).then(async res => {
          console.log('-----', await res.text())
        }).catch(async err => {
          console.log('-----', err.message)
          const p = request.frame().page()
          // notifyApiResponseError(p, err.message)
          await checkSwalAndInit(page, sweetAlertFile)
          try {
            tip(p)
          } catch(e) {

          }
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
          console.log('-----', err.message)
          const p = await request.frame().page()
          await checkSwalAndInit(page, sweetAlertFile)
          try{
            tip(p)
          } catch(e) {

          }
        })
      }
    })
  })
  
  await page.goto("https://idiot-alex.github.io/api-gen/")

  // await checkSwalAndInit(page, sweetAlertFile)
  // tip(page)
}

const checkSwalAndInit = async (page, scriptFile) => {
  let flag = await page.evaluate(() => ('Swal' in window))
  if (!flag) {
    await page.addScriptTag({path: scriptFile})
  }
  flag = await page.evaluate(() => ('Swal' in window))
  Promise.resolve(flag)
}

const tip = async (page) => {
  try {
    
  await page.evaluate(() => {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })
    
    Toast.fire({
      icon: 'error',
      title: 'Signed in successfully'
    })
    // Swal.fire({
    //   title: '操作成功',
    //   icon: 'success',
    //   timer: 3000,
    //   showConfirmButton: false
    // })
  })
} catch(e) {

}
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