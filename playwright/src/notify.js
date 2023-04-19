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

/**
 * execFunc
 * @param {*} page 
 * @param {*} func 
 * @returns 
 */
export function execFunc(page, func) {
  return new Promise((resolve, reject) => {
    checkSwalAndInitSwal(page).then(async res => {
      if (!res) {
        return
      }
      // exec func
      try {
        const res = await func()
        resolve(res)
      } catch(err) {
        console.error('function exec error: ', err.message)
        reject(err)
      }
    })
  })
}

/**
 * toast
 * @param {*} page 
 * @param {*} icon 'success' 'error' ...
 * @param {*} message 
 */
export async function toast(page, icon, message) {
  const func = async () => { 
    await page.evaluate(({icon, message}) => {
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
        icon: icon,
        title: message
      })
    }, {icon, message})

    return Promise.resolve(true)
  }

  const res = await execFunc(page, func)

  return Promise.resolve(res)
}
