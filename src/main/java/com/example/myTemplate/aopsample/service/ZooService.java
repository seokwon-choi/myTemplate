package com.example.myTemplate.aopsample.service;

import com.example.myTemplate.aopsample.dto.AnimalDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ZooService {
    public ResponseEntity<AnimalDto> getAnimal() {
        AnimalDto animalDto  = AnimalDto.builder()
                                        .name("Lion")
                                        .code("1234")
                                        .secretCode("new1234!")
                                        .build();
        return ResponseEntity.ok().body(animalDto);
    }
}
