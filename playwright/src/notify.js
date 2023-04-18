const checkSwalAndInit = async (page) => {
  // 获取 SweetAlert2 文件的绝对路径
  const sweetAlertFile = 'src/lib/sweetalert2.all.min.js'

  let flag = await page.evaluate(() => ('Swal' in window))
  if (!flag) {
    await page.addScriptTag({path: sweetAlertFile})
  }
  flag = await page.evaluate(() => ('Swal' in window))
  Promise.resolve(flag)
}

export async function notifyApiResponseError(page, message) {
  console.log(message)
  // const errorMsg = message.split('\n')[0]?.split(':')[1]
  // const errUrl = message.split('\n')[2]

  // console.log(errorMsg)

  await checkSwalAndInit(page)
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
      title: '请求处理失败',
      // text: '.......'
    })
  })
}

export function notifyError(message) {
  const errorMsg = message.split('\n')[0]
  notifier.notify({
    title: 'Error',
    message: `${errorMsg}`
  });
}