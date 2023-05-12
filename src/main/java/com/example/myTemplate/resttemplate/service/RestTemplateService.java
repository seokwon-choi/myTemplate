package com.example.myTemplate.resttemplate.service;

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
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());


        return result.getBody();

    }

}
