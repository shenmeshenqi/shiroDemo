package com.zzh.shirotest.demo.service;

import com.zzh.shirotest.demo.domain.Permission;

import java.util.List;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 上午 11:49
 */
public interface PermissionService {
    /**
     * 根据用户名查找用户具有的权限
     * @param username 用户名不会重复
     * @return
     */
    List<Permission> selectUserPermissionByUsername(String username);

    /**
     * 根据用户名查找用户具有的权限
     * @param userid 用户名不会重复
     * @return
     */
    List<Permission> selectUserPermissionByUserid(Integer userid);

}
