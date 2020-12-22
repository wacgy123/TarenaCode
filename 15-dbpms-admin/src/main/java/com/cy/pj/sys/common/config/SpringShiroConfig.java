package com.cy.pj.sys.common.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration//此类实例由Spring创建和管理，取代.xml文件来配置
public class SpringShiroConfig {
    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(2*1800000L);//默认为30分钟
        //不重写URL地址
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    @Bean
    public RememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setMaxAge(7*24*60*60);
        cookieRememberMeManager.setCookie(cookie);
        return cookieRememberMeManager;
    }
    /**
     * 配置授权缓存管理器，减少授权时频繁的访问数据库查询用户权限信息操作
     */
    @Bean
    public CacheManager shiroCacheManager(){
        return new MemoryConstrainedCacheManager();
    }
    /**
     * 配置SecurityManager,此对象时Shiro框架的核心，负责认证和授权。
     * 当由Spring框架整合一个第三方的bean对象时，这个类型不是我们自己写的，
     * 我们无法在类上直接使用类似@Component的注解进行描述，那么如何整合这样的bean呢？
     * 解决方案：自己在Spring中的配置类(@Configuration)中定义方法，
     * 在方法内部构建对象实例，并且由@Bean注解对方法进行描述即可，
     * 这个注解描述的方法其返回值会交给Spring管理，其bean的名字默认为方法名，
     * 也可以通过@Bean注解的value属性来进行定义名字。
     */
    @Bean
    public SecurityManager securityManager(Realm realm,CacheManager cacheManager,RememberMeManager rememberMeManager,SessionManager sessionManager){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * 配置认证过滤规则，例如哪些资源需要认证访问，哪些资源可以匿名访问。
     * 规则的检验在shiro框架中借助大量过滤器去实现的。
     * Shiro框架中提供了过滤器类型，但是基于其类型创建实例需要通过过滤器工厂
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean=new ShiroFilterFactoryBean();
        //在map中存储规则，key为资源名，value为规则
        Map<String,String> map=new LinkedHashMap<>();
        map.put("/bower_components/**","anon");//anno表示可以匿名访问
        map.put("/build/**","anon");
        map.put("/dist/**","anon");
        map.put("/plugins/**","anon");
        map.put("/user/doLogin", "anon");
        map.put("/doLogout","logout");//logout会自动跳转登录页面
        //map.put("/**", "authc");//authc表示除了以上资源都需认证访问
        map.put("/**", "user");//user表示还可以从客服端的cookie中获取用户信息
        filterFactoryBean.setFilterChainDefinitionMap(map);
        //通过securityManager这个Bean可以判定访问的资源已经认证过
        filterFactoryBean.setSecurityManager(securityManager);
        //设置未认证访问跳转的url
        filterFactoryBean.setLoginUrl("/doLoginUI");
        return filterFactoryBean;
    }

    /**
     * 这里的Advisor负责找到@RequiresPermissions注解描述的方法，
     * 这些方法为授权访问的切入点方法，当执行这些方法时会由通知(Advice)对象
     * 调用SecurityManager完成权限检测与授权。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
