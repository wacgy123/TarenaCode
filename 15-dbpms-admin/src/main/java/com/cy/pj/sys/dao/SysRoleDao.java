package com.cy.pj.sys.dao;

import com.cy.pj.common.pojo.CheckBox;
import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.pojo.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface SysRoleDao {
    @Select("select id,name from sys_roles")
    List<CheckBox> findObjects();
    int deleteObject(Integer id);
    int updateObject(SysRoleMenu sysRoleMenu);
    int insertObject(SysRole entity);
    int getRowCount(String name);
    List<SysRole> findPageObjects(String name,Integer startIndex, Integer pageSize);
}
