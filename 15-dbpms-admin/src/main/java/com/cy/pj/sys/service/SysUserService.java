package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.pojo.SysUserDept;
import com.cy.pj.sys.pojo.SysUserMenu;

import java.util.List;
import java.util.Map;

public interface SysUserService {
    int updatePassword(String password,String newPassword,String cfgPassword);
    int updateObject(SysUser entity,Integer[] roleIds);
    Map<String,Object> findById(Integer id);
    int saveObject(SysUser entity,Integer[] roleIds);
    int validById(Integer id,Integer valid);
    PageObject<SysUserDept> findPageObjects(String username,Integer pageCurrent);
}
