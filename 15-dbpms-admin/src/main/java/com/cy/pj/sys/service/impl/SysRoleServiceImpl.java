package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.pojo.CheckBox;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.pojo.SysRoleMenu;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public List<CheckBox> findObjects() {
        return sysRoleDao.findObjects();
    }

    @Override
    public int deleteById(Integer id) {
        sysRoleMenuDao.deleteById(id);
        return sysRoleDao.deleteObject(id);
    }

    @Override
    public int updateObject(SysRoleMenu sysRoleMenu) {
        int rows=sysRoleDao.updateObject(sysRoleMenu);
        if(rows==0)
            throw new ServiceException("更新失败");
        sysRoleMenuDao.deleteById(sysRoleMenu.getId());
        return sysRoleMenuDao.insertObjects(sysRoleMenu.getId(),sysRoleMenu.getMenuIds());
    }

    @Override
    public SysRoleMenu findById(Integer id) {
        return sysRoleMenuDao.findById(id);
    }

    @Override
    public int saveObject(SysRole entity, List<Integer> menuIds) {
        int rows=sysRoleDao.insertObject(entity);
        sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
        return rows;
    }

    @Override
    public int getRoleCount(String name) {
        return sysRoleDao.getRowCount(name);
    }

    @Override
    public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
        if(pageCurrent==null||pageCurrent<1){
            throw new IllegalArgumentException("页码值不正确，页码应大于1");
        }
        int rowCount=sysRoleDao.getRowCount(name);
        if(rowCount==0) {
            throw new ServiceException("没有找到对应的记录");
        }
        int pageSize=2;
        Integer startIndex=(pageCurrent-1)*pageSize;
        List<SysRole> records = sysRoleDao.findPageObjects(name,startIndex, pageSize);
        return new PageObject<>(rowCount, records, pageSize, pageCurrent);
    }
}
