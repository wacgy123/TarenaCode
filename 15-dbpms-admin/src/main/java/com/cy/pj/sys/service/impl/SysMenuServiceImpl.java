package com.cy.pj.sys.service.impl;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.pojo.SysUserMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public List<SysUserMenu> findUserMenusByUserId(Integer userId) {
        List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(userId);
        List<Integer> menuIds = sysRoleMenuDao.findMenuIdsByRoleIds(roleIds);
        return sysMenuDao.findMenusByIds(menuIds);
    }

    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenuDao.findZtreeMenuNodes();
    }

    @Override
    public List<Map<String, Object>> findObjects() {
        return sysMenuDao.findObjects();
    }

    @Override
    public int insertObject(SysMenu sysMenu) {
        return sysMenuDao.insertObject(sysMenu);
    }

    @Override
    public int updateObject(SysMenu sysMenu) {
        return sysMenuDao.updateObject(sysMenu);
    }
}
