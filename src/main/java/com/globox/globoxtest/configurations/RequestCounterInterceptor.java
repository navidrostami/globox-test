package com.globox.globoxtest.configurations;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RequestCounterInterceptor implements HandlerInterceptor {

    private AtomicInteger requestCount = new AtomicInteger(0);

    public int getRequestCount() {
        return requestCount.get();
    }

    @Override
    public boolean preHandle(
            HttpServletRequest  request,
            HttpServletResponse response,
            Object handler) {
        requestCount.incrementAndGet();
        return true;
    }

}
