package com.globox.globoxtest.controllers;

import com.globox.globoxtest.configurations.RequestCounterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCounterController {

    @Autowired
    private RequestCounterInterceptor requestCounterInterceptor;

    @GetMapping("/request-count")
    public ResponseEntity<Integer> getRequestCount() {
        return ResponseEntity.ok(requestCounterInterceptor.getRequestCount());
    }

}