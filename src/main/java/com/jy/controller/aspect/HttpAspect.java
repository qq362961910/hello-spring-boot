package com.jy.controller.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class HttpAspect {

    private static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.jy.controller.*.*(..))")
    public void log() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //url
        logger.info("url: {}", request.getRequestURL());
        //method
        logger.info("method: {}", request.getMethod());
        //ip
        logger.info("ip: {}", request.getRemoteAddr());
        //类方法
        logger.info("class-method: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args: {}", joinPoint.getArgs());
    }
    @After("log()")
    public void doAfter() {
        logger.info("do after...");
    }

    @AfterReturning(pointcut = "log()", returning = "obj")
    public void afterReturning(Object obj) {
        logger.info("response: {}", obj);
    }

}
