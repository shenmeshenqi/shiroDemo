package com.zzh.shirotest.demo;

import com.zzh.shirotest.demo.domain.Permission;
import com.zzh.shirotest.demo.domain.Role;
import com.zzh.shirotest.demo.domain.User;
import com.zzh.shirotest.demo.service.PermissionService;
import com.zzh.shirotest.demo.service.RoleService;
import com.zzh.shirotest.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.zzh.shirotest.demo.dao.mapper")
class DemoApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Test
    void contextLoads() {
        User zhangsan = userService.selectByUsername("zhangsan");
        System.out.println(zhangsan);
    }
    
    @Test
    void fun2(){
        User zhangsan = userService.selectByUsername("zhangsan");
        List<Role> roles = roleService.selectUserRoleByUserid(zhangsan.getUserid());
        for (Role role :
                roles) {
            System.out.println(role);

        }
    }
    @Test
    void fun3(){
        User zhangsan = userService.selectByUsername("zhangsan");
        List<Permission> permissions = permissionService.selectUserPermissionByUserid(zhangsan.getUserid());
        for (Permission permission:
             permissions) {
            System.out.println(permission);

        }
    }

}
