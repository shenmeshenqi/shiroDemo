package com.zzh.shirotest.demo.service;

import com.zzh.shirotest.demo.domain.User;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/17 0017 下午 20:53
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
