package com.example.myTemplate.resttemplate.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Req<T> {

    private Header header;

    private T body;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Header {
        private String responseCode;
    }
}
