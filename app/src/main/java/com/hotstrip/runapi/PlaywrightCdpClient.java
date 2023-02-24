package com.hotstrip.runapi;

import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class PlaywrightCdpClient implements InitializingBean, DisposableBean {

    private Playwright playwright;

    public Page connect() {
        Browser browserCdp = playwright.chromium()
                .connectOverCDP("http://localhost:9222/");
//        Page page = browserCdp.newPage();

        new Thread(() -> {
            try {
                Thread.sleep(500);
                log.info("........");
                browserCdp.contexts().forEach(browserContext -> {
                    browserContext.pages().forEach(page -> {
                        page.onRequest(request -> log.info(">> " + request.method() + " " + request.url()));
                        page.onResponse(response -> log.info("<<" + response.status() + " " + response.url()));
                    });
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();

        BrowserContext defaultContext = browserCdp.contexts().get(0);
        Page page = defaultContext.pages().get(0);


        page.navigate("https://chengchaos.github.io/");
        return page;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        playwright = Playwright.create();
    }

    @Override
    public void destroy() throws Exception {
        playwright.close();
    }
}
