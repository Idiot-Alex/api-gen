// const http = require("http");

const { chromium } = require("playwright");

const app = async() => {
  const browser = await chromium.launch({
    args: ["--remote-debugging-port=9222"],
    headless: false,
  });

  // const page = await browser.newPage();

  // http.get("http://localhost:9999");
}

const cdp = async () => {
  const browser = await chromium.connectOverCDP('http://127.0.0.1:9222/');
  // let ctx = browser.contexts()[0];
  // let page = await ctx.newPage();
  
  const page = await browser.newPage();
  
  page.on("request", (request) => console.log(">>", request.method(), request.url(), request.headers()))
  page.on("response", (response) => console.log("<<", response.status(), response.url()))
  
  await page.goto("https://www.google.com/")
  
}

(async() => {
  await app()
  await cdp()
})()