package com.example.myTemplate.user.controller;

import com.example.myTemplate.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/my")
@Slf4j
public class UserController {

    @GetMapping("/get")
    public UserDto get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){

        UserDto userDto = new UserDto();
        userDto.setName(name).setAge(age);

        int a = 10+age;



        return userDto;
    }

    @PostMapping("/post")
    public UserDto post(@Valid @RequestBody UserDto userDto ){

        log.info(userDto.toString());

        return  userDto;

    }

    /*
    1. 특정 클래스에만 예외처리를 하고 싶을 때 class안에 직접 만들어준다.
    2. 공통부로 빠진 예외처리보다 우선순위가 높아 여기서 예외처리를 타면 공통부 예외처리를 타지 않는다.

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    */

}
