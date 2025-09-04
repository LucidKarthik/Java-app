package com.example.demo.controller;

import com.example.demo.service.HelloService;
import com.example.demo.model.HelloMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    private final HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public HelloMessage sayHello(@RequestParam(defaultValue = "World") String name) {
        return service.getMessage(name);
    }
}

