package com.hotstrip.runapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

@Slf4j
@SpringBootTest
public class ApplicationTests {


    @Test
    public void test() throws Exception {
        URL url = new URL("http://127.0.0.1/api/v1/test");
        log.info("url path: {}", url.getPath());
        log.info("url host: {}", url.getHost());
        log.info("url port: {}", url.getPort());
        log.info("url default port: {}", url.getDefaultPort());
        log.info("url query: {}", url.getQuery());
    }

}
