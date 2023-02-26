// const http = require("http");

const { chromium } = require("playwright");
const axios = require("axios")

// const instance = axios({
//   baseURL: 'http://127.0.0.1:8080',
//   timeout: 3000,
//   headers: {
//     'Content-type': 'application/json;charset=UTF-8'
//   }
// });

const app = async() => {
  const browser = await chromium.launch({
    args: ["--remote-debugging-port=9222"],
    headless: false,
  });
}

const cdp = async () => {
  const browser = await chromium.connectOverCDP('http://127.0.0.1:9222/');
  
  const page = await browser.newPage();


  browser.contexts().forEach(browserContext => {
    browserContext.pages().forEach(page => {
      page.on('requestfailed', async(request) => {
        const response = await request.response()
        if (!response) {
          return;
        }
    
        const data = {
          url: request.url(),
          method: request.method(),
          request: {
            resourceType: request.resourceType(),
            // headers: request.allHeaders(),
            postData: request.postData(),
            failed: true,
            errorText: request.failure().errorText
          },
          response: {
            // headers: response.allHeaders(),
            // text: text,
            status: response.status(),
            statusText: response.statusText()
          }
        }
        axios.post('http://127.0.0.1:8080/api/upload', data).then((res) => {
          console.log(res)
        })
      })
      page.on('requestfinished', async(request) => {
        const response = await request.response()
        let text = ''

        if (response.status() !== 302) {
          const contentType = response.headers()['content-type'];
          console.log(contentType)
          if (contentType && (contentType.includes('text/') || contentType === 'application/json')) {
            if (response.ok()) {
              try {
                text = await response.text();
              } catch (error) {
                console.log('error: ', error)
              }
              console.log(text);  
            }
          }
        }

        // console.log(text)
    
        const data = {
          url: request.url(),
          method: request.method(),
          request: {
            resourceType: request.resourceType(),
            headers: request.headers(),
            postData: request.postData(),
            failed: false
          },
          response: {
            headers: response.headers(),
            text: text,
            status: response.status(),
            statusText: response.statusText()
          }
        }
        axios.post('http://127.0.0.1:8080/api/upload', data).then((res) => {
          console.log(res)
        })
      })
    })
  });
  
  // page.on("request", (request) => console.log(">>", request.method(), request.url()))
  // page.on("response", (response) => console.log("<<", response.status(), response.url()))

  
  await page.goto("https://www.google.com/")
}

(async() => {
  await app()
  await cdp()
})()