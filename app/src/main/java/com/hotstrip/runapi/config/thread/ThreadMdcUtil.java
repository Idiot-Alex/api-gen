package com.hotstrip.runapi.config.thread;

import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

public final class ThreadMdcUtil {
    private static final String TRACE_ID = "TRACE_ID";

    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    public static void setTraceIdIfAbsent() {
        if (StrUtil.isBlank(MDC.get(TRACE_ID))) {
            MDC.put(TRACE_ID, generateTraceId());
        }
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> contextMap) {
        return () -> {
            if (null == contextMap) {
                MDC.clear();
            } else {
                MDC.setContextMap(contextMap);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> contextMap) {
        return () -> {
            if (null == contextMap) {
                MDC.clear();
            } else {
                MDC.setContextMap(contextMap);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
