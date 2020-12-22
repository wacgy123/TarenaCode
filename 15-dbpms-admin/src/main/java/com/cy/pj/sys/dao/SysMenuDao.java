package com.cy.pj.sys.dao;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.pojo.SysUserMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuDao {
    List<SysUserMenu> findMenusByIds(List<Integer> menuIds);
    List<String> findPermissions(List<Integer> menuIds);
    List<Map<String,Object>> findObjects();
    @Select("select id,name,parentId from sys_menus")
    List<Node> findZtreeMenuNodes();
    int insertObject(SysMenu sysMenu);
    int updateObject(SysMenu sysMenu);
}
