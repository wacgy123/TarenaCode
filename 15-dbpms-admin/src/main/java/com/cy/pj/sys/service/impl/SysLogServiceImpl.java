package com.cy.pj.sys.service.impl;

import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.pojo.SysUserDept;
import com.cy.pj.sys.service.SysLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public PageObject<SysLog> findPageObjects(String username,Integer pageCurrent) {
        if(pageCurrent==null||pageCurrent<1)
            throw new IllegalArgumentException("当前页码值错误");
        int pageSize=10;
        Page<SysLog> page= PageHelper.startPage(pageCurrent,pageSize);
        List<SysLog> records=sysLogDao.findPageObjects(username);
        return new PageObject<>((int)page.getTotal(), records, pageSize, pageCurrent);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveObject(SysLog entity) {
        String tName=Thread.currentThread().getName();
        System.out.println("SysLogServiceImpl.saveObject.thread.name="+tName);
        sysLogDao.insertObject(entity);
    }
}
