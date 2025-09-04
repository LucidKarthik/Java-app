package com.example.demo.service;

import com.example.demo.model.HelloMessage;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public HelloMessage getMessage(String name) {
        return new HelloMessage("Hello, " + name + "!");
    }
}

