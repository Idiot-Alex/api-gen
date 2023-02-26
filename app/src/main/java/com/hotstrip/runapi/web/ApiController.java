package com.hotstrip.runapi.web;

import com.hotstrip.runapi.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/upload")
    public String upload(@RequestBody Api api) {
        log.info(api.toString());
        return "ok";
    }

}
