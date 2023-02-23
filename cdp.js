// const CDP = require('chrome-remote-interface');

// async function intercept(url) {
//   let client;
//   try {
//     client = await CDP();
//     const { Network, Page, Fetch } = client;
    
//     // 所有网络请求发出前触发，打印能看到请求的url
//     Network.requestWillBeSent((params) => {
//       console.log(params);
//     });
    
//     // Fetch.requestPaused((params) => {
//     //   // 拦截到请求并返回自定义body
//     //   Fetch.fulfillRequest({
//     //     requestId: params.requestId,
//     //     responseCode: 200,
//     //     body: Buffer.from(JSON.stringify({ name: 'Thomas' })).toString(
//     //       'base64'
//     //     ),
//     //   });
//     // });
    
//     // // 符合以下模式的请求才会Fetch.requestPaused事件
//     // await Fetch.enable({
//     //   patterns: [
//     //     {
//     //       urlPattern: '*/api/user',
//     //       resourceType: 'XHR',
//     //     },
//     //   ],
//     // });
    
//     // 允许跟踪网络，这时网络事件可以发送到客户端
//     await Network.enable();
    
//     await Page.enable();
//     await Page.navigate({ url });
//     // 等待页面加载
//     await Page.loadEventFired();
//   } catch (err) {
//     console.error(err);
//   }
// }
// // 我在本地3000端口起了服务，随便换一个网址也能看到页面各种资源的请求
// intercept('http://www.baidu.com/');
// intercept('http://www.google.com/');

const { chromium } = require("playwright");
(async () => {
const browser = await chromium.connectOverCDP('http://127.0.0.1:9222/');
// let ctx = browser.contexts()[0];
// let page = await ctx.newPage();

const page = await browser.newPage();

page.on("request", (request) => console.log(">>", request.method(), request.url()))
page.on("response", (response) => console.log("<<", response.status(), response.url()))

await page.goto("https://www.google.com/")

})()