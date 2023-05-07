package com.example.myTemplate.common.config;

import com.example.myTemplate.common.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //.addPathPatterns("url 패턴")는 해당 패턴의 url만 인터셉터 적용
        //.excludePathPatterns("url 패턴")는 해당 패턴의 url만 제외하고 인터셉터 적용
        registry.addInterceptor(authInterceptor);//.addPathPatterns().excludePathPatterns()
        //밑에 addInterceptor를 사용하면 위에 것 먼저 실행되고 밑에 것으로 순차적으로 진행된다!
    }
}
