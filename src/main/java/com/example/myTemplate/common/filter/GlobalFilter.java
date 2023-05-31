package com.example.myTemplate.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 우선 필터를 통해 HttpServletResponse, HttpServletRequest 클래스로 들어온 request와 response를 ContentCachingRequestWrapper와 ContentCachingResponseWrapper로 래핑해주어야 한다.
        // HttpServletRequest 그대로 request.getReader 함수를 호출하거나 안에 있는 데이터를 읽으려고 하면, 단 한번만 읽을 수 있도록 톰캣에서 만들어두었기 때문에 이걸 다시 읽을 수 있는 클래스로 래핑해주어야 하기 때문이다.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
        // 생성 시에는 read를 하지 않고 길이만 초기화를 시켜준다

        //RequestBodyWrapper 만들어 사용하면 doFilter전에서 Wrapper를 사용할 수 있다.
        RequestBodyWrapper requestWrapper = new RequestBodyWrapper((HttpServletRequest) request);
        String body = requestWrapper.getRequestBody();
        //body 출력 가능

        // 전처리
        chain.doFilter(httpServletRequest, httpServletResponse);
        // doFilter가 실행이 되면서 실내 내부 Spring 안으로 들어가서야
        // writeToCache 메소드가 실행이 되서 request의 내용이
        // content에 담겨 있게 되면서 읽을 수 있게 된다
        // 그렇기에 log는 doFilter 이후에 처리해 준다

        // 후처리
        // req
        String url = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        log.info("request url : {}, request body : {}", url, reqContent);

        // res
        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        //copyBodyToResponse를 해줘야 클라이언트가 정상적으로 응답을 받을 수 있다
        httpServletResponse.copyBodyToResponse();

        log.info("response status : {}, response body : {}", httpStatus, resContent);

    }
}
