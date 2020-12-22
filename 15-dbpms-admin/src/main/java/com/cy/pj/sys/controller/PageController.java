package com.cy.pj.sys.controller;

import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.pojo.SysUserMenu;
import com.cy.pj.sys.service.SysMenuService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/doLoginUI")
    public String doLoginUI(){
        return "login";
    }

//    @GetMapping("menu/menu_list")
//    public String doMenuUI(){
//        return "sys/menu_list";
//    }

    @GetMapping("/{module}/{moduleUI}")
    public String doModuleUI(@PathVariable String moduleUI){
        return "sys/"+moduleUI;
    }

    @GetMapping("/doIndexUI")
    public String doIndexUI(Model model){
        //获取登录用户对象(底层从session获取)
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String loginUser=user.getUsername();
        model.addAttribute("username", loginUser);
        List<SysUserMenu> userMenus = sysMenuService.findUserMenusByUserId(user.getId());
        model.addAttribute("userMenus", userMenus);
        return "starter";
    }

    @GetMapping("/doPageUI")
    public String doPageUI(){
        return "common/page";
    }
}
