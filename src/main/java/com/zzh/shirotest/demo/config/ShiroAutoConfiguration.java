package com.zzh.shirotest.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zzh.shirotest.demo.config.shiroRealm.ShiroRealm;
import lombok.Data;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 下午 13:14
 */
@Configuration
@ConfigurationProperties(prefix = "shiro")
@Data
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(value = {SecurityManager.class})
public class ShiroAutoConfiguration {

    private String hashAlgorithmName="md5";
    private Integer hashIterations=2;
    private String salt;
    private String loginUrl;
    //登出页面 对应logout
    private String loginOut;
    //放行url 对应 anon
    private List<String> anonUrls;
    //拦截url 对应 authc
    private List<String> authcs;
    private static  final String SHIRO_FILTER="shiroFilter";
    private static final String SHIRO_DIALETC="shiroDialect";

    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }
    
    @Bean
    public ShiroRealm shiroRealm(CredentialsMatcher credentialsMatcher){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setSalt(salt);
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }

    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm);
        return defaultWebSecurityManager;
    }
    
    @Bean(name=SHIRO_FILTER)
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        Map<String,String> map = new HashMap<>();
        if(loginOut!=null)
            map.put(loginOut,"logout");
        mapAddList(map,anonUrls,"anon");
        mapAddList(map,authcs,"authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }
    private void mapAddList(Map<String,String> map,List<String> list,String value){
        if(null==map||list==null||list.size()==0)
        return;
        for (String str :
             list) {
            map.put(str,value);
        }
    }

    //将过滤器注册
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy(){
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetBeanName(SHIRO_FILTER);
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxyFilterRegistrationBean = new FilterRegistrationBean<>();
        delegatingFilterProxyFilterRegistrationBean.setFilter(delegatingFilterProxy);
        return delegatingFilterProxyFilterRegistrationBean;
    }


    //开启注解 advisor
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean(name=SHIRO_DIALETC)
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
