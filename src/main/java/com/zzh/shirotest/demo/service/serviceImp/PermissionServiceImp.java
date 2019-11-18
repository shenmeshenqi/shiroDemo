package com.zzh.shirotest.demo.service.serviceImp;

import com.zzh.shirotest.demo.dao.mapper.PermissionMapper;
import com.zzh.shirotest.demo.domain.Permission;
import com.zzh.shirotest.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 下午 12:37
 */

@Service
public class PermissionServiceImp implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据用户名查找用户具有的权限
     *
     * @param username 用户名不会重复
     * @return
     */
    @Override
    public List<Permission> selectUserPermissionByUsername(String username) {
        return this.permissionMapper.selectUserPermissionByUsername(username);
    }

    /**
     * 根据用户名查找用户具有的权限
     *
     * @param userid 用户名不会重复
     * @return
     */
    @Override
    public List<Permission> selectUserPermissionByUserid(Integer userid) {
        return this.permissionMapper.selectUserPermissionByUserid(userid);
    }
}
