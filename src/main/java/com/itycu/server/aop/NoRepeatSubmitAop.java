package com.itycu.server.aop;

import com.google.common.cache.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
/**
 * @功能描述 aop解析注解
 * @author www.gaozz.club
 * @date 2018-08-26
 */
public class NoRepeatSubmitAop {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private Cache<String, Integer> cache;

    @Around("execution(* com.itycu.server.controller..*Controller.*(..))") // && (@annotation(nrs) || @annotation(pnrs))
    public Object arround(ProceedingJoinPoint pjp) throws Throwable{  //, PutMapping nrs,PostMapping pnrs
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
            HttpServletRequest request = attributes.getRequest();
//            String key = sessionId + "-" + request.getServletPath();
            String key = request.getServletPath();
            if(request.getQueryString() !=null){
                key += request.getQueryString();
            }
            String token = request.getHeader("token");
            if(token!=null){
                key += token;
            }
//            if(sessionId!=null){
//                key += sessionId;
//            }
//            System.out.println("key:" + key);
            if (cache.getIfPresent(key) == null) {// 如果缓存中有这个url视为重复提交
                cache.put(key, 0);
                Object o = pjp.proceed();
                return o;
            } else {
                log.error("RepeatSubmit:" + key);
                throw new NullPointerException("系统正在处理，请等待处理完成！");
//                return null;
            }
        } catch (Throwable e) {
//            e.printStackTrace();
            throw e;
//            log.error("验证重复提交时出现未知异常!");
//            return "{\"code\":-889,\"message\":\"验证重复提交时出现未知异常!\"}";
        }
    }

}
