package com.zzh.shirotest.demo.config.shiroRealm;

import com.zzh.shirotest.demo.domain.Permission;
import com.zzh.shirotest.demo.domain.Role;
import com.zzh.shirotest.demo.domain.User;
import com.zzh.shirotest.demo.service.PermissionService;
import com.zzh.shirotest.demo.service.RoleService;
import com.zzh.shirotest.demo.service.UserService;
import com.zzh.shirotest.demo.utils.ActivierUser;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 下午 13:19
 */
@Data
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    private String salt;

    public static void main(String[] args) {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(null);
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ActivierUser activierUser  =(ActivierUser) principalCollection.getPrimaryPrincipal();
        if(null!=activierUser){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (Role role:
                 activierUser.getRoles()) {
                simpleAuthorizationInfo.addRole(role.getRolename());
            }
            for (Permission permission:
                 activierUser.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPercode());
            }
            System.out.println(simpleAuthorizationInfo.getStringPermissions());
            return simpleAuthorizationInfo;
        }
        return null;
    }

    //身份信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        User user = userService.selectByUsername(username);
        if(null!=user){
            List<Role> roles = roleService.selectUserRoleByUserid(user.getUserid());
            List<Permission> permissions = permissionService.selectUserPermissionByUserid(user.getUserid());
            ActivierUser activierUser = new ActivierUser(user, roles, permissions);
            if(salt==null||"".equals(salt)){
                salt = ""+user.getUsername()+user.getAddress();
            }
            ByteSource bytes = ByteSource.Util.bytes(salt);
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activierUser,user.getUserpwd(),bytes,this.getClass().getSimpleName());
            return authenticationInfo;
        }
        return null;
    }

}
