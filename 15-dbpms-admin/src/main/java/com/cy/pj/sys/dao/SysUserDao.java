package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.pojo.SysUserDept;
import com.cy.pj.sys.pojo.SysUserMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SysUserDao {
    int updatePassword(String password,String salt,Integer id);
    @Select("select * from sys_users where username=#{username}")
    SysUser findUserByUsername(String username);
    int updateObject(SysUser entity);
    @Select("select * from sys_users where id=#{id}")
    SysUser findById(Integer id);
    int insertObject(SysUser entity);
    @Update("update sys_users set valid=#{valid},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}")
    int validById(Integer id,Integer valid,String modifiedUser);
    List<SysUserDept> findPageObjects(String username);
}
