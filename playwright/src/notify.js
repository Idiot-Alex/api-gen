import notifier from 'node-notifier'

export function notifyApiResponseError(message) {
  const errorMsg = message.split('\n')[0]?.split(':')[1]
  const errUrl = message.split('\n')[2]
  notifier.notify({
    title: 'Error',
    message: `${errorMsg} \n ${errUrl}`
  });
}

export function notifyError(message) {
  const errorMsg = message.split('\n')[0]
  notifier.notify({
    title: 'Error',
    message: `${errorMsg}`
  });
}