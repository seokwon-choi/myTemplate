package com.example.myTemplate.resttemplate.service;

import com.example.myTemplate.resttemplate.dto.ReqUserDto;
import com.example.myTemplate.resttemplate.dto.ResUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

}
