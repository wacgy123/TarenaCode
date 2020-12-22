package com.cy.pj.sys.service.aspect;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.util.IPUtils;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

    @Pointcut("bean(sysUserServiceImpl)")//bean(spring容器中bean的名字,定义为bean对象名字是sysUserServiceImpl所有方法的集合)
    public void doLog(){}

    @Around("doLog()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
        long t1=System.currentTimeMillis();
        log.info("start {}", t1);
        try {
            Object result = joinPoint.proceed();
            long t2 = System.currentTimeMillis();
            log.info("end {}", t2);
            saveUserLog(joinPoint,t2-t1);
            return result;
        }catch (Throwable e){
            log.error("error {}",e.getMessage());
            throw e;
        }
    }

    private void saveUserLog(ProceedingJoinPoint joinPoint, long time) throws NoSuchMethodException, JsonProcessingException {
        Class<?> clazz=joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method=clazz.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        RequiredLog annotation = method.getAnnotation(RequiredLog.class);
        String methods=clazz.getName()+"."+method.getName();
        String operation=annotation.operation();
        String params= new ObjectMapper().writeValueAsString(joinPoint.getArgs());
        SysLog log=new SysLog();
        log.setIp(IPUtils.getIpAddr());
        SysUser user=(SysUser) SecurityUtils.getSubject().getPrincipal();
        String username=user.getUsername();
        log.setUsername(username);
        log.setOperation(operation);
        log.setMethod(methods);
        log.setParams(params);
        log.setTime(time);
        sysLogService.saveObject(log);
    }
}
