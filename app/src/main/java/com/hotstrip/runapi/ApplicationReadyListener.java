package com.hotstrip.runapi;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private PlaywrightServer playwrightServer;
    @Resource
    private PlaywrightCdpClient cdpClient;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            playwrightServer.run();
            Page page = cdpClient.connect();
            new Thread(() -> {
                page.onRequest(request -> log.info(">> " + request.method() + " " + request.url()));
                page.onResponse(response -> log.info("<<" + response.status() + " " + response.url()));
            }).start();
//            page.navigate("https://chengchaos.github.io/");
        } catch (Exception e) {

        }
    }
}
