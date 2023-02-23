window.onload = () => {
  console.log(window.ah)
  window.ah.proxy({
    onRequest: (config, handler) => {
      console.log(config)
      handler.next(config);
    },
    onError: (err, handler) => {
      console.log(err)
      handler.next(err)
    },
    onResponse: (response, handler) => {
      console.log(response)
      handler.next(response)
    }
  }, window)
}

function test(url) {
  var events = ['load', 'loadend', 'timeout', 'error', 'readystatechange', 'abort']

  var xhr = new XMLHttpRequest();

  //setTimeout(()=>xhr.abort(),100)
  xhr.open('get', url, true);
  xhr.send();
  events.forEach(function (e) {
      xhr['on' + e] = function (event) {
          console.log('on' + e, xhr.readyState, event)
      }
      xhr.addEventListener(e, function (event) {
          console.log(e, xhr.readyState, event)
      })
  });
  xhr.addEventListener('load', function (event) {
      console.log('response', xhr.response)
  })

}

// test(location.href)