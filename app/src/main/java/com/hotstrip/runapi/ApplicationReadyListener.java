package com.hotstrip.runapi;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private PlaywrightServer playwrightServer;
    @Resource
    private PlaywrightCdpClient cdpClient;
    @Resource
    private Playwright1 playwright1;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
//            playwrightServer.run();
//            Browser browser = cdpClient.connect();

//            Browser browser = playwright1.runCdp();

            Playwright playwright = Playwright.create();

            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setArgs(Collections.singletonList("--remote-debugging-port=9222"));
            playwright.chromium().launch(options);

            Browser browser = playwright.chromium()
                    .connectOverCDP("http://127.0.0.1:9222");
            Page page = browser.newPage();

            Executors.newSingleThreadExecutor().submit(() -> {
                while (true) {
                    log.info(".....");
                    Thread.sleep(1000);
                    browser.contexts().forEach(browserContext -> {
                        browserContext.pages().forEach(page1 -> {
                            page1.offRequestFinished(request -> {
                                Executors.newSingleThreadExecutor().submit(() -> {
                                    log.info(".>>--------- " + request.method() + " " + request.url() + " " + request.headers());
                                });
                            });
//                    page1.onRequestFinished(request -> {
//                        Executors.newSingleThreadExecutor().submit(() -> {
////                            while (true) {
////                                Thread.sleep(1000);
//                                log.info(".>> " + request.method() + " " + request.url() + " " + request.headers());
////                            }
//                        });
//                    });
//                    page1.onRequestFailed(request -> {
//                        Executors.newSingleThreadExecutor().submit(() -> {
////                            while (true) {
////                                Thread.sleep(1000);
//                                log.info(".>> " + request.method() + " " + request.url() + " " + request.headers());
////                            }
//                        });
//                    });
                            page1.onRequest(request -> {
                                Executors.newSingleThreadExecutor().submit(() -> {
//                            while (true) {
//                                Thread.sleep(1000);
                                    log.info(".>> " + request.method() + " " + request.url() + " " + request.headers());
//                            }
                                });
                            });
                            page1.onResponse(response -> {
                                Executors.newSingleThreadExecutor().submit(() -> {
//                            while (true) {
//                                Thread.sleep(1000);
                                    log.info(".<<" + response.status() + " " + response.url());
//                            }
                                });
                            });
                        });
                    });
                }
            });

            page.navigate("https://gitee.com/hotstrip");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage(), e);
        }
    }
}
