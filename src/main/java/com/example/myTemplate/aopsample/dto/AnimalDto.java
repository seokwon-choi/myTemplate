package com.example.myTemplate.aopsample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalDto {
    private String name;
    private String code;
    private String secretCode;
}
