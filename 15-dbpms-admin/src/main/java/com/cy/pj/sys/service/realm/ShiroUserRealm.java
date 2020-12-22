package com.cy.pj.sys.service.realm;

import com.cy.pj.sys.dao.*;
import com.cy.pj.sys.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 定义realm类型继承授权AuthorizingRealm类型，
 * 假如只做认证可以直接继承AuthenticatingRealm
 * Shiro框架中Realm对象可以理解为用户获取认证数据信息和授权信息的一个对象
 */
@Service
public class ShiroUserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    /**
     * 此方法用于获取用户的权限信息并进行封装
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        List<Integer> roleIds= sysUserRoleDao.findRoleIdsByUserId(user.getId());
        if(roleIds==null||roleIds.size()==0)
            throw new AuthorizationException();
        List<Integer> menuIds=sysRoleMenuDao.findMenuIdsByRoleIds(roleIds);
        if(menuIds==null||menuIds.size()==0)
            throw new AuthorizationException();
        List<String> permissions=sysMenuDao.findPermissions(menuIds);
        if(permissions==null||permissions.size()==0)
            throw new AuthorizationException();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Set<String> setPermissions=new HashSet<>();
        for (String per:permissions){
            if(per!=null&&!"".equals(per)){
                setPermissions.add(per);
            }
        }
        info.setStringPermissions(setPermissions);
        return info;
    }

    /**
     * 此方法获取用户认证信息并进行封装
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        SysUser sysUser=sysUserDao.findUserByUsername(username);
        if(sysUser==null)
            throw new UnknownAccountException();
        if(sysUser.getValid()==0)
            throw new LockedAccountException();
        ByteSource credentialSalt=ByteSource.Util.bytes(sysUser.getSalt());
        return new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),credentialSalt,getName());
    }

    /**
     * 比对密码时需要调用此方法获取加密算法相关信息
     */
    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher("MD5");
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;
    }
}
