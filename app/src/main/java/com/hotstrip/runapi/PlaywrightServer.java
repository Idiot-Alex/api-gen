package com.hotstrip.runapi;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

@Slf4j
@Component
public class PlaywrightServer implements InitializingBean, DisposableBean {
    @Resource
    private Playwright playwright;

    public void run() {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Collections.singletonList("--remote-debugging-port=9222"));
        Browser browser = playwright.chromium().launch(options);
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
