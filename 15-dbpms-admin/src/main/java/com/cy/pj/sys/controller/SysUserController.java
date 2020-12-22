package com.cy.pj.sys.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/user/doUpdatePassword")
    public JsonResult doUpdatePassword(String pwd,String newPwd,String cfgPwd){
        sysUserService.updatePassword(pwd, newPwd, cfgPwd);
        return new JsonResult("update ok");
    }

    @RequestMapping("/user/doLogin")
    public JsonResult doLogin(String username,String password,boolean isRememberMe){
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        usernamePasswordToken.setRememberMe(isRememberMe);
        subject.login(usernamePasswordToken);
        if (subject.isAuthenticated()) {
            return new JsonResult("login ok");
        } else {
            usernamePasswordToken.clear();
            return new JsonResult("login failed");
        }
    }

    @RequestMapping("/user/doUpdateObject")
    public JsonResult doUpdateObject(SysUser entity, Integer[] roleIds){
        sysUserService.updateObject(entity,roleIds);
        return new JsonResult("update ok");
    }
    @RequestMapping("/user/doFindObjectById")
    public JsonResult doFindObjectById(Integer id){
        return new JsonResult(sysUserService.findById(id));
    }

    @RequestMapping("/user/doSaveObject")
    public JsonResult doSaveObject(SysUser entity,Integer[] roleIds){
        sysUserService.saveObject(entity, roleIds);
        return new JsonResult("save ok");
    }

    @RequestMapping("/user/doFindPageObjects")
    public JsonResult doFindPageObjects(String username, Integer pageCurrent){
        return new JsonResult(sysUserService.findPageObjects(username,pageCurrent));
    }

    @RequestMapping("/user/doValidById")
    public JsonResult doValidById(Integer id,Integer valid){
        sysUserService.validById(id, valid);
        return new JsonResult("update ok");
    }
}
