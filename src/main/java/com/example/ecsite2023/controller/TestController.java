package com.example.ecsite2023.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/sample")
    public String helloWorld() {
        return "Hello World";
    }
}
