package com.example.myTemplate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
public class myController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
