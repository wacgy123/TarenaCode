package com.cy.pj.common.web;

import com.cy.pj.common.pojo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShiroException.class)
    public JsonResult doHandleShiroException(ShiroException e){
        JsonResult result=new JsonResult();
        result.setState(0);
        if(e instanceof UnknownAccountException){
            result.setMessage("账户不存在");
        }else if(e instanceof LockedAccountException){
            result.setMessage("账户已被禁用");
        }else if(e instanceof IncorrectCredentialsException){
            result.setMessage("账户密码不正确");
        }else if(e instanceof UnauthorizedException){
            result.setMessage("没有此操作的权限");
        }else{
            result.setMessage("系统故障，请稍后访问");
        }
        e.printStackTrace();
        return result;
    }

    @ExceptionHandler(RuntimeException.class)
    public JsonResult doHandlerRuntimeException(RuntimeException e){
        log.error("exception {}",e.getMessage());
        e.printStackTrace();
        return new JsonResult(e);
    }
}
