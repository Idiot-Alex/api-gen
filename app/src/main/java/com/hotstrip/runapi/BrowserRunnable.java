package com.hotstrip.runapi;

import com.microsoft.playwright.Browser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserRunnable implements  Runnable{
    private Browser browser;

    public BrowserRunnable(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                log.info("........{}", browser.isConnected());
                log.info("........context size: {}", browser.contexts().size());

                browser.contexts().forEach(browserContext -> {
                    browserContext.pages().forEach(page1 -> {
                        page1.onRequest(request -> log.info(".>> " + request.method() + " " + request.url() + " " + request.headers()));
                        page1.onResponse(response -> log.info(".<<" + response.status() + " " + response.url()));
                    });
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
