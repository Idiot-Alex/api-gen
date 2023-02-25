package com.hotstrip.runapi;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Playwright1 {

    public Browser runCdp() {
        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Collections.singletonList("--remote-debugging-port=9222"));
        Browser browser = playwright.chromium().launch(options);

        Browser browserCdp = playwright.chromium()
                .connectOverCDP("http://127.0.0.1:9222");
        return browserCdp;
    }
}
