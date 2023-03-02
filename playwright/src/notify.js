import notifier from 'node-notifier'

export function notifyError(message) {
  notifier.notify({
    title: 'Error',
    message: message
  });
}