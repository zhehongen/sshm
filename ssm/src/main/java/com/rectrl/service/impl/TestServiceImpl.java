package com.rectrl.service.impl;

import com.rectrl.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    public String test() {
        return "test";
    }
}