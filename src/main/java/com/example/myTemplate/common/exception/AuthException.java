package com.example.myTemplate.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthException extends RuntimeException{
    //GlobalExceptionHandler에서 예외 핸들링 시 message를 가져오고 싶으면 e.getMessage하면 된다.
    private String message;
}
