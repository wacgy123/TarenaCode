package com.cy.pj.sys.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysTimeAspect {
    //@Pointcut("bean(sysUserServiceImpl)")
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
    public void doTime(){}

    @Before("doTime()")
    public void doBefore(){
        System.out.println("@Before");
    }

    @After("doTime()")
    public void doAfter(){
        System.out.println("@After");
    }

    @AfterReturning("doTime()")
    public void doAfterReturning(){
        System.out.println("@AfterReturning");
    }

    @AfterThrowing("doTime()")
    public void doAfterThrowing(){
        System.out.println("@AfterThrowing");
    }

    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
        System.out.println("@Around.before");
        try {
            Object result = joinPoint.proceed();
            System.out.println("@Around.afterReturning");
            return result;
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println("@Around.afterThrowing");
            throw e;
        }finally {
            System.out.println("@Around.after");
        }
    }
}
