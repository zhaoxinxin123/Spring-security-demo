package com.study.two_security_jwt.config;

import com.study.two_security_jwt.exception.CommonException;
import com.study.two_security_jwt.model.JwtUser;
import com.study.two_security_jwt.model.Role;
import com.study.two_security_jwt.model.User;
import com.study.two_security_jwt.service.RolePermissionService;
import com.study.two_security_jwt.service.UserRoleService;
import com.study.two_security_jwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author ZXX
 * @version 1.0
 * @date 2020/12/23 11:05 上午
 * @desc
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user= userService.findByName(name);
        if (user==null){
            throw new CommonException("用户不存在111");
        }
        //查询用户角色
        Role role= userRoleService.getOneByUserId(user.getId());
        //查询用户权限
        Set<String> permissions =rolePermissionService.findByRoleId(role.getId());
        System.out.println("角色信息"+role.toString());
        return new JwtUser(user.getUsername(),user.getPassword(),user.getFullname(),permissions,role.getDescription());
    }
}
