package com.zzh.shirotest.demo.utils;

import com.zzh.shirotest.demo.domain.Permission;
import com.zzh.shirotest.demo.domain.Role;
import com.zzh.shirotest.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 下午 13:52
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActivierUser implements Serializable {
   private User user;
   private List<Role> roles;
   private List<Permission> permissions;
}
