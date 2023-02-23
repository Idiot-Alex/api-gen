// const http = require("http");

const { chromium } = require("playwright");

(async () => {
  const browser = await chromium.launch({
    args: ["--remote-debugging-port=9222"],
    headless: false,
  });

  const page = await browser.newPage();

  // http.get("http://localhost:9999");
})();