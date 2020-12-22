package com.cy.pj.sys.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
@PostMapping
    @RequestMapping("/menu/doFindObjects")
    public JsonResult doFindObjects(){
        return new JsonResult(sysMenuService.findObjects());
    }

    @RequestMapping("/menu/doFindZtreeMenuNodes")
    public JsonResult doFindZtreeMenuNodes(){
        return new JsonResult(sysMenuService.findZtreeMenuNodes());
    }

    @RequestMapping("/menu/doSaveObject")
    public JsonResult doSaveObject(SysMenu sysMenu){
        return new JsonResult(sysMenuService.insertObject(sysMenu));
    }

    @RequestMapping("/menu/doUpdateObject")
    public JsonResult doUpdateObject(SysMenu sysMenu){
        return new JsonResult(sysMenuService.updateObject(sysMenu));
    }
}
