package com.study.two_security_jwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 测试资源1 * @return */
    @GetMapping(value = "/r/r1")
    @ResponseBody
    @PreAuthorize("hasAuthority('p1')")
    public String r1(){
        return getUsername()+" 访问资源1"; }
    /**
     * 测试资源2 * @return */
    @GetMapping(value = "/r/r2")
    @ResponseBody
    @PreAuthorize("hasAuthority('p2')")
    public String r2(){
        return getUsername()+" 访问资源2"; }

    /**
     * 测试资源2 * @return */
    @GetMapping(value = "/r/r3")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public String r3(){
        return getUsername()+" 访问资源3"; }

    @RequestMapping("/login‐success")
    @ResponseBody
    public String loginSuccess(String name){
        return "登录成功";
    }
    @RequestMapping("/login‐fail")
    @ResponseBody
    public String loginFail(String name){
        return "用户名或密码错误";
    }


    //获取当前用户信息
    private String getUsername(){
        String username = null;
        //当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份
        Object principal = authentication.getPrincipal();
        if(principal == null){
            username = "匿名";
        }
        if(principal instanceof org.springframework.security.core.userdetails.UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }else{
            username = principal.toString();
        }
        return username;
    }

}

