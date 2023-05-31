package com.example.myTemplate.resttemplate.controller;

import com.example.myTemplate.resttemplate.dto.Req;
import com.example.myTemplate.resttemplate.dto.ReqUserDto;
import com.example.myTemplate.resttemplate.dto.ResUserDto;
import com.example.myTemplate.resttemplate.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        return restTemplateService.user();
    }

    @GetMapping("/exchange")
    public ResUserDto exchangeUser(){//@RequestBody ReqUserDto reqUserDto
        return restTemplateService.exchangeUser();
    }

    @GetMapping("/generic")
    public Req<ResUserDto> genericExchangeUser(){//@RequestBody ReqUserDto reqUserDto
        log.info("%%%%%%%genericExchangeUser%%%%%");
        return restTemplateService.genericExchangeUser();
    }
}
