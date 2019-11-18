package com.zzh.shirotest.demo.controller;

import com.zzh.shirotest.demo.utils.ActivierUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author zhuzh
 * @version 1.0
 * @date 2019/11/18 0018 下午 15:06
 */
@Controller
public class LoginController {

    @RequestMapping("/login/toLogin")
    public String toLogin(){
        return "index";
    }

    @RequestMapping("/login/login")
    public String login(String username, String password, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try {
            subject.login(usernamePasswordToken);
            System.out.println("登陆成功");
            ActivierUser activierUser =(ActivierUser) subject.getPrincipal();
            System.out.println(activierUser.getPermissions());
            session.setAttribute("user",activierUser.getUser());
            return "redirect:/user/toUserManager";
        }
        catch (AuthenticationException ex){
            ex.printStackTrace();
            return "redirect:/index.html";
        }

    }
    
    //在shiro中配置了登出url不用再写
   /* @RequestMapping("/login/loginOut")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }*/
}
