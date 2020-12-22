package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.pojo.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuDao {
    List<Integer> findMenuIdsByRoleIds(List<Integer> roleIds);
    int deleteById(Integer id);
    SysRoleMenu findById(Integer id);
    int insertObjects(Integer roleId,List<Integer> menuIds);
}
