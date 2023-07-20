package vn.com.msb.aop.aspect;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.msb.aop.entity.Account;

// Example logging
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut(value = "execution(* vn.com.msb.aop.controller.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut() && args(request)")
    public void beforeAdvice(JoinPoint joinPoint, Account request) {
        // Get the request mapping information
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RequestMapping requestMapping = methodSignature.getMethod().getAnnotation(RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            String[] apiUrls = requestMapping.value();
            String apiUrl = apiUrls[0];
            log.info(">>>>> API Method: {} - Request: {}", apiUrl, new Gson().toJson(request));
        }
    }

    @AfterReturning(value = "pointCut()", returning = "response")
    public void afterReturnAdvice(JoinPoint joinPoint, ResponseEntity<?> response) {
        // Get the request mapping information
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RequestMapping requestMapping = methodSignature.getMethod().getAnnotation(RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            String[] apiUrls = requestMapping.value();
            log.info(">>>>> API Method: {} - Response: {}", apiUrls[0], new Gson().toJson(response.getBody()));
        }
    }


}
