package com.zzh.shirotest.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 下午 15:21
 */
@Controller
public class UserController {

    @RequestMapping("/user/toUserManager")
    public String toUserManager(){
        return "userView";
    }
}
