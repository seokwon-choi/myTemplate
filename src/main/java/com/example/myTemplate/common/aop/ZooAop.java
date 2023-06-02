package com.example.myTemplate.common.aop;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.myTemplate.aopsample.dto.AnimalDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class ZooAop {

    //aop가 실행될 메소드 정의
    @Pointcut("execution(* com.example.myTemplate.aopsample.service..*.getAnimal*(..))")
    public void getAnimal(){}

    /*
        @Before - 메소드 실행하기 이전
        @After - 메소드 실행 후(예외가 발생되더라도 실행)
        @Around - (Before + After)
        @AfterReturning - 메소드 실행 성공할 때
        @AfterThrowing - 메소드 실행에서 예외 발생할 때
    */

    //aop 로직 -> SecretCode 마스킹 처리하기
    @AfterReturning(pointcut = "getAnimal()", returning = "result")
    public void getAnimalAfterReturning(JoinPoint joinPoint, ResponseEntity<AnimalDto> result){
        log.info("AOP - getAnimalAfterReturning - Start");

        //AOP에서 Request 가져오는 방법
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
        String mask = "*";
        result.getBody().setSecretCode(mask.repeat(result.getBody().getSecretCode().length()));
    }
}
