package com.zzh.shirotest.demo.service.serviceImp;

import com.zzh.shirotest.demo.dao.mapper.RoleMapper;
import com.zzh.shirotest.demo.domain.Role;
import com.zzh.shirotest.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 下午 12:38
 */
@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户名查找用户所具有的角色
     *
     * @param username
     * @return
     */
    @Override
    public List<Role> selectUserRoleByUsername(String username) {
        return this.roleMapper.selectUserRoleByUsername(username);
    }

    /**
     * 根据用户id查找用户所具有的角色
     *
     * @param userid
     * @return
     */
    @Override
    public List<Role> selectUserRoleByUserid(Integer userid) {
        return this.roleMapper.selectUserRoleByUserid(userid);
    }
}
