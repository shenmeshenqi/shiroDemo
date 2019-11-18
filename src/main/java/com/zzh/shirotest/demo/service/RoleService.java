package com.zzh.shirotest.demo.service;

import com.zzh.shirotest.demo.domain.Role;

import java.util.List;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 上午 11:48
 */
public interface RoleService {
    /**
     * 根据用户名查找用户所具有的角色
     * @param name
     * @return
     */
    List<Role> selectUserRoleByUsername(String name);

    /**
     * 根据用户id查找用户所具有的角色
     * @param userid
     * @return
     */
    List<Role> selectUserRoleByUserid(Integer userid);
}
