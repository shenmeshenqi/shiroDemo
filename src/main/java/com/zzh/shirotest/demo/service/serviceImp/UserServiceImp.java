package com.zzh.shirotest.demo.service.serviceImp;

import com.zzh.shirotest.demo.dao.mapper.UserMapper;
import com.zzh.shirotest.demo.domain.User;
import com.zzh.shirotest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 上午 11:57
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User selectByUsername(String username) {
       return this.userMapper.selectByUsername(username);
    }
}
