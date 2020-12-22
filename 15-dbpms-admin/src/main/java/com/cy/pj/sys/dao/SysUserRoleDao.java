package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRoleDao {
    @Delete("delete from sys_user_roles where user_id=#{id}")
    int deleteObjectsByUserId(Integer id);
    @Select("select role_id from sys_user_roles where user_id=#{id}")
    List<Integer> findRoleIdsByUserId(Integer id);
    int insertObjects(Integer userId,Integer[] roleIds);
}
