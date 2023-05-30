package com.example.myTemplate.resttemplate.controller;

import com.example.myTemplate.resttemplate.dto.ReqUserDto;
import com.example.myTemplate.resttemplate.dto.ResUserDto;
import com.example.myTemplate.resttemplate.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ApiController {

    private final RestTemplateService restTemplateService;

    @GetMapping("")
    public String getHello(){

        return restTemplateService.hello();
    }

    @GetMapping("/new")
    public ResUserDto postUser(){//@RequestBody ReqUserDto reqUserDto
        System.out.println("#######controller######");
        return restTemplateService.user();
    }
}
