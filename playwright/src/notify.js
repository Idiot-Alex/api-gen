const checkSwalAndInitSwal = async (page) => {
  // 获取 SweetAlert2 文件的绝对路径
  const sweetAlertFile = 'src/lib/sweetalert2.all.min.js'

  let flag = await page.evaluate(() => ('Swal' in window))
  if (!flag) {
    await page.addScriptTag({path: sweetAlertFile})
  }
  flag = await page.evaluate(() => ('Swal' in window))
  return Promise.resolve(flag)
}

export function toast(page, message) {
  checkSwalAndInitSwal(page).then(async (res) => {
    if (!res) {
      return
    }
    try {
      await page.evaluate((msg) => {
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
          title: msg
        })
      }, message)
    } catch(err) {
      console.error('function toast error: ', err.message)
    }
  })
}
