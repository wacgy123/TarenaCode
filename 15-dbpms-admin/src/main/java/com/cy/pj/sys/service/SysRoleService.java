package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.CheckBox;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.pojo.SysRoleMenu;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface SysRoleService {
    List<CheckBox> findObjects();
    int deleteById(Integer id);
    int updateObject(SysRoleMenu sysRoleMenu);
    SysRoleMenu findById(Integer id);
    int saveObject(SysRole entity,List<Integer> menuIds);
    int getRoleCount(String name);
    PageObject<SysRole> findPageObjects(String name, Integer pageCurrent);
}
