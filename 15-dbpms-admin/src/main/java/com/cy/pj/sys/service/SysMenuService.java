package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.pojo.SysUserMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService {
    List<SysUserMenu> findUserMenusByUserId(Integer userId);
    List<Node> findZtreeMenuNodes();
    List<Map<String,Object>> findObjects();
    int insertObject(SysMenu sysMenu);
    int updateObject(SysMenu sysMenu);
}
