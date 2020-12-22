package com.cy.pj.sys.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.pojo.SysRoleMenu;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/role/doFindRoles")
    public JsonResult doFindObjects(){
        return new JsonResult(sysRoleService.findObjects());
    }

    @RequestMapping("/role/doFindPageObjects")
    public JsonResult doFindPageObjects(String name, Integer pageCurrent){
        return new JsonResult(sysRoleService.findPageObjects(name, pageCurrent));
    }

    @RequestMapping("/role/doSaveObject")
    public JsonResult doSaveObject(SysRole entity, List<Integer> menuIds){
        return new JsonResult(sysRoleService.saveObject(entity, menuIds));
    }

    @RequestMapping("/role/doFindObjectById")
    public JsonResult doFindObjectById(Integer id){
        return new JsonResult(sysRoleService.findById(id));
    }

    @RequestMapping("/role/doUpdateObject")
    public JsonResult doUpdateObject(SysRoleMenu sysRoleMenu){
        return new JsonResult(sysRoleService.updateObject(sysRoleMenu));
    }

    @RequestMapping("/role/doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        return new JsonResult(sysRoleService.deleteById(id));
    }
}
