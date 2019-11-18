package com.zzh.shirotest.demo.dao.mapper;

import com.zzh.shirotest.demo.domain.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer perid);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer perid);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

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