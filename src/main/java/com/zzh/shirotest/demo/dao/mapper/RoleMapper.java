package com.zzh.shirotest.demo.dao.mapper;

import com.zzh.shirotest.demo.domain.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    /**
     * 根据用户名查找用户所具有的角色
     * @param username
     * @return
     */
    List<Role> selectUserRoleByUsername(String username);

    /**
     * 根据用户id查找用户所具有的角色
     * @param userid
     * @return
     */
    List<Role> selectUserRoleByUserid(Integer userid);
}