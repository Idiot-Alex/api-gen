package com.hotstrip.runapi;

import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class PlaywrightCdpClient implements InitializingBean, DisposableBean {

    @Resource
    private Playwright playwright;
    private Page page;

    public Browser connect() {
        Browser browserCdp = playwright.chromium()
                .connectOverCDP("http://127.0.0.1:9222");

        return browserCdp;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        playwright = Playwright.create();
    }

    @Override
    public void destroy() throws Exception {
        playwright.close();
    }
}
