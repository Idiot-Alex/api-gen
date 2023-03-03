package com.hotstrip.runapi.interceptor;

import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * log interceptor
 * @author hotstrip
 * @since 2023-03-03
 */
@Component
public class LogInterceptor implements HandlerInterceptor {
    private static final String TRACE_ID = "TRACE_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = UUID.randomUUID().toString();
        // 从请求头里面获取 traceId
        String headerTraceId = request.getHeader(TRACE_ID);
        if (StrUtil.isNotBlank(headerTraceId)) {
            traceId = headerTraceId;
        }
        MDC.put(TRACE_ID, traceId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TRACE_ID);
    }
}
