const http = require('http');
const { chromium } = require('playwright');

const PROXY_PORT = 8000; // 代理服务器监听的端口号
const TARGET_URL = 'https://www.baidu.com'; // 目标服务器的URL

http.createServer((req, res) => {
  const requestUrl = new URL(req.url, `http://${req.headers.host}`);
  const targetUrl = new URL(TARGET_URL);

  // 创建Playwright浏览器上下文
  chromium.launch().then(browser => {
    browser.newContext().then(context => {
      const page = context.newPage();

      // 设置请求路由
      context.route(requestUrl.href, (route, request) => {
        console.log(route)
        // 使用Playwright页面对象发出代理请求
        page.goto(requestUrl.href, { timeout: 60000 }).then(response => {
          const responseBody = response.body();
          route.fulfill({
            status: response.status(),
            headers: response.headers(),
            body: responseBody
          });
        }).catch(error => {
          console.error(error);
          route.abort();
        });
      });

      // 使用Playwright页面对象打开目标URL
      page.goto(targetUrl.href, { timeout: 60000 }).then(() => {
        
        page.close();
        context.close();
        browser.close();
      }).catch(error => {
        console.error(error);
        page.close();
        context.close();
        browser.close();
      });
    });
  });
}).listen(PROXY_PORT);

console.log(`代理服务器已启动，监听端口：${PROXY_PORT}`);
