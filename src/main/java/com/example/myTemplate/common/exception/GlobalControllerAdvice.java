package com.example.myTemplate.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//특정 패키지에만 적용 시
//@RestControllerAdvice(basePackages = "com.example.myTemplate.user.controller")

//특정 클래스에만 적용 시
//@RestControllerAdvice(basePackageClasses = "UserController.class")

@RestControllerAdvice()
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){

        System.out.println("---------------------");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println("---------------------");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
