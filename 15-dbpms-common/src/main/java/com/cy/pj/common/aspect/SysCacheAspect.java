package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class SysCacheAspect {
    private final Map<String,Object> cache=new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.cy.pj.common.annotation.Cache)")
    public void doCache(){}

    @Pointcut("@annotation(com.cy.pj.common.annotation.ClearCache)")
    public void doClearCache(){}

    @AfterReturning("doClearCache()")
    public void doAfter(){
        System.out.println("清除缓存!");
        cache.clear();
    }

    @Around("doCache()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
        String className=joinPoint.getTarget().getClass().getName();
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method=joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        String key=className+method.getName();
        Object result=cache.get(key);
        if(result!=null){
            System.out.println("从缓存取出数据!");
            return result;
        }else {
            result=joinPoint.proceed();
            cache.put(key, result);
            return result;
        }
    }
}
