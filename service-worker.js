function logURL(requestDetails) {
  console.log(`Loading: ${requestDetails.url}`);
}

chrome.webRequest.onBeforeRequest.addListener(
  logURL,
  {urls: ["http://*/*", "https://*/*"]}
);