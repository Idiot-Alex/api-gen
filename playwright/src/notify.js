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

export function execFunc(page, func) {
  checkSwalAndInitSwal(page).then(async res => {
    if (!res) {
      return
    }
    // exec func
    try {
      func()
    } catch(err) {
      console.error('function exec error: ', err.message)
    }
  })
}

export function toast(page, message) {
  const func = async () => { 
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
  }
  execFunc(page, func)
}

export function initDialog(page) {
  const func = async () => {
    await page.evaluate(() => {
      let timerInterval
      Swal.fire({
        title: 'Auto close alert!',
        html: 'I will close in <b></b> milliseconds.',
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
          Swal.showLoading()
          const b = Swal.getHtmlContainer().querySelector('b')
          timerInterval = setInterval(() => {
            b.textContent = Swal.getTimerLeft()
          }, 100)
        },
        willClose: () => {
          clearInterval(timerInterval)
        }
      }).then((result) => {
        /* Read more about handling dismissals below */
        if (result.dismiss === Swal.DismissReason.timer) {
          console.log('I was closed by the timer')
        }
      })
    })
  }
  execFunc(page, func)
}
