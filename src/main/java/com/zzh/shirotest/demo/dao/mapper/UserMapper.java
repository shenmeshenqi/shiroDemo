package com.zzh.shirotest.demo.dao.mapper;

import com.zzh.shirotest.demo.domain.Permission;
import com.zzh.shirotest.demo.domain.Role;
import com.zzh.shirotest.demo.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User selectByUsername(String username);


}