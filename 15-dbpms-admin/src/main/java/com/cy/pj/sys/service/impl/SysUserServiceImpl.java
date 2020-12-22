package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.pojo.SysUserDept;
import com.cy.pj.sys.pojo.SysUserMenu;
import com.cy.pj.sys.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(readOnly = false,rollbackFor = Throwable.class,isolation = Isolation.READ_COMMITTED)
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    @RequiredLog(operation = "修改密码")
    public int updatePassword(String password, String newPassword, String cfgPassword) {
        if(StringUtils.isEmpty(newPassword))
            throw new IllegalArgumentException("新密码不能为空");
        if(StringUtils.isEmpty(cfgPassword))
            throw new IllegalArgumentException("确认密码不能为空");
        if (!newPassword.equals(cfgPassword))
            throw new IllegalArgumentException("两次输入的密码不相等");
        if(StringUtils.isEmpty(password))
            throw new IllegalArgumentException("原密码不能为空");
        SysUser user=(SysUser) SecurityUtils.getSubject().getPrincipal();
        SimpleHash sh=new SimpleHash("MD5",password,user.getSalt(),1);
        if(!user.getPassword().equals(sh.toHex()))
            throw new IllegalArgumentException("原密码不正确");
        String salt=UUID.randomUUID().toString();
        sh=new SimpleHash("MD5",newPassword,salt,1);
        int rows=sysUserDao.updatePassword(sh.toHex(), salt, user.getId());
        if(rows==0)
            throw new ServiceException("修改失败");
        return rows;
    }

    @Override
    public int updateObject(SysUser entity, Integer[] roleIds) {
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getUsername()))
            throw new IllegalArgumentException("用户名不能为空");
        if(roleIds==null||roleIds.length==0)
            throw new IllegalArgumentException("必须要为用户分配角色");
        int rows=sysUserDao.updateObject(entity);
        if(rows==0)
            throw new ServiceException("更新失败");
        sysUserRoleDao.deleteObjectsByUserId(entity.getId());
        sysUserRoleDao.insertObjects(entity.getId(), roleIds);
        return rows;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> findById(Integer id) {
        if(id==null||id<1)
            throw new IllegalArgumentException("参数不合法");
        SysUser sysUser=sysUserDao.findById(id);
        if(sysUser==null)
            throw new ServiceException("此用户已经不存在");
        List<Integer> roleIds=sysUserRoleDao.findRoleIdsByUserId(id);
        Map<String,Object> map=new HashMap<>();
        map.put("user", sysUser);
        map.put("roleIds", roleIds);
        return map;
    }

    @Transactional
    @Override
    public int saveObject(SysUser entity, Integer[] roleIds) {
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getUsername()))
            throw new IllegalArgumentException("用户名不能为空");
        if(StringUtils.isEmpty(entity.getPassword()))
            throw new IllegalArgumentException("密码不能为空");
        if(roleIds==null||roleIds.length==0)
            throw new IllegalArgumentException("必须要为用户分配角色");
        String salt= UUID.randomUUID().toString();
        SimpleHash simpleHash=new SimpleHash("MD5",entity.getPassword(),salt,1);
        String hashedPassword=simpleHash.toHex();
        entity.setPassword(hashedPassword);
        entity.setSalt(salt);
        int rows=sysUserDao.insertObject(entity);
        sysUserRoleDao.insertObjects(entity.getId(), roleIds);
        return rows;
    }

    @RequiresPermissions("sys:user:update")
    @RequiredLog(operation = "禁用或启用账号")
    @Override
    public int validById(Integer id, Integer valid) {
        if(id==null||id<1)
            throw new IllegalArgumentException("id值无效");
        if(valid==null||valid!=0&&valid!=1)
            throw new IllegalArgumentException("状态值不正确");
        int rows=sysUserDao.validById(id, valid, "admin");
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }

    @Override
    @RequiredLog(operation = "分页查询用户信息")
    public PageObject<SysUserDept> findPageObjects(String username,Integer pageCurrent) {
        if(pageCurrent==null||pageCurrent<1)
            throw new IllegalArgumentException("当前页码值错误");
        int pageSize=3;
        Page<SysUserDept> page=PageHelper.startPage(pageCurrent,pageSize);
        List<SysUserDept> records=sysUserDao.findPageObjects(username);
        return new PageObject<>((int)page.getTotal(), records, pageSize, pageCurrent);
    }
}
