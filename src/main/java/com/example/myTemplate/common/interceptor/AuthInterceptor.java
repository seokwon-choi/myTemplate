package com.example.myTemplate.common.interceptor;

import com.example.myTemplate.common.annotation.Auth;
import com.example.myTemplate.common.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    //preHandler() : Controller 실행 전
    //postHandler() : Controller 실행 후, View Rendering 실행 전
    //afterCompletion() : View Rendering 후

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        ContentCachingRequestWrapper  httpServletRequest = new ContentCachingRequestWrapper(request);
        String reqUri = httpServletRequest.getRequestURI();
        String query = httpServletRequest.getQueryString();
        log.info("request uri : {}", reqUri);
        log.info("request query : {}", query);

        URI uri = UriComponentsBuilder.fromUriString(reqUri)
                                        .query(query)
                                        .build()
                                        .toUri();

        boolean hasAuth = checkAnnotation(handler, Auth.class);
        log.info("has Auth annotation : {}", hasAuth);

        //나의 서버는 모두 public으로 동작하는데
        //단! Auth 권한을 가진 요청에 대해서는 세션, 쿠키를 검사하겠다.
        if(hasAuth){
            // 권한체크
            String q = uri.getQuery();
            if(query.equals("name=jerry")){
                return true;
            }
            throw new AuthException("name is not jerry");
        }

        //return true가 되어야 controller로 넘어간다.
        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz){
        // resource (ex-> javascript, html)이면 통과
        //instanceof는 객체 타입을 확인 -> x instanceof Car 일 때 x가 Car이면 true 아니면 false
        //ResourceHttpRequestHandler는 정적 리소스를 처리한다
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        // annotation이 달려 있는
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //handlerMethod.getMethodAnnotation(clazz) -> 현재 호출된 핸들러 메서드에 해당 어노테이션이 존재하는지 체크(controller 클래스안에 있는 메소드들에 걸려있는 어노테이션 확인)
        //handlerMethod.getBeanType().getAnnotation(clazz) -> 현재 호출된 핸들러 클래스에 해당 어노테이션이 존재하는지 체크(controller 클래스에 걸려있는 어노테이션 확인)
        //clazz에는 MyCustomAnnotation.class가 들어간다.
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            //Auth 어노테이션이 있으면 true
            return true;
        }
        return false;
    }
}
