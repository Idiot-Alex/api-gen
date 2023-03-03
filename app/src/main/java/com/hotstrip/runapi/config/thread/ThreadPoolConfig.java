package com.hotstrip.runapi.config.thread;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * thread pool config
 * @author hotstrip
 * @since 2023-03-03
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    // 声明一个线程池
    public Executor asyncServiceExecutor() {
        MyThreadPoolTaskExecutor executor = new MyThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("async-service-MDC-");
        executor.initialize();
        return executor;
    }

}
