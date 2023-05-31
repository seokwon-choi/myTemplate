package com.example.myTemplate.resttemplate.service;

import com.example.myTemplate.resttemplate.dto.Req;
import com.example.myTemplate.resttemplate.dto.ReqUserDto;
import com.example.myTemplate.resttemplate.dto.ResUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    // http://localhost/api/server/hello
    public String hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8082")
                .path("/test/api/server/hello")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class);
        //getForEntity는 http의 get메소드를 Entity로 가져오겠다라는 의미이다.
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());


        return result.getBody();

    }

    public ResUserDto user(){

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8082")
                .path("/test/api/server/user")
                .encode()
                .build()
                //.expand("jerry", 20) -> pathvariable 같은 경우 path에 {name} 변수 선언 후 expand에 순서대로 값을 넣는다.
                .toUri();
        System.out.println(uri.toString());

        ReqUserDto userReq = new ReqUserDto();
        userReq.setAge(20);
        userReq.setName("jerry");
        userReq.setNumber(123);

        RestTemplate restTemplate = new RestTemplate();
        System.out.println(userReq);
        ResponseEntity<ResUserDto> response = restTemplate.postForEntity(uri, userReq, ResUserDto.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }

    public ResUserDto exchangeUser(){

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8082")
                .path("/test/api/server/user")
                .encode()
                .build()
                //.expand("jerry", 20) -> pathvariable 같은 경우 path에 {name} 변수 선언 후 expand에 순서대로 값을 넣는다.
                .toUri();
        System.out.println(uri.toString());

        ReqUserDto userReq = new ReqUserDto();
        userReq.setAge(20);
        userReq.setName("jerry");
        userReq.setNumber(123);

        RequestEntity<ReqUserDto> requestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(userReq);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResUserDto> response = restTemplate.exchange(requestEntity, ResUserDto.class);
        return response.getBody();
    }

    public Req<ResUserDto> genericExchangeUser(){
        log.info("%%%%%%%genericExchangeUserService%%%%%");

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8082")
                .path("/test/api/server/user")
                .encode()
                .build()
                //.expand("jerry", 20) -> pathvariable 같은 경우 path에 {name} 변수 선언 후 expand에 순서대로 값을 넣는다.
                .toUri();
        System.out.println(uri.toString());

        ReqUserDto userReq = new ReqUserDto();
        userReq.setAge(20);
        userReq.setName("jerry");
        userReq.setNumber(123);

        Req req = new Req();
        req.setHeader(new Req.Header());
        req.setBody(userReq);

        RequestEntity<Req<ReqUserDto>> requestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);
        log.info("#######1##########");
        RestTemplate restTemplate = new RestTemplate();
        log.info("#######2##########");
        var tmp = new ParameterizedTypeReference<Req<ResUserDto>>(){};
        ResponseEntity<Req<ResUserDto>> response = restTemplate.exchange(requestEntity, tmp);
        log.info("#######3##########");
        return response.getBody();
    }

}
