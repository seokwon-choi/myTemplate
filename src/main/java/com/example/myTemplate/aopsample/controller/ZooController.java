package com.example.myTemplate.aopsample.controller;

import com.example.myTemplate.aopsample.dto.AnimalDto;
import com.example.myTemplate.aopsample.service.ZooService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/zoo")
public class ZooController {

    private final ZooService zooService;

    @GetMapping("/animal")
    public ResponseEntity<AnimalDto> getAnimal(){
        return zooService.getAnimal();
    }

}
