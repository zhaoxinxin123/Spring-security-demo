package com.study.two_security_jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.two_security_jwt.model.User;
import com.study.two_security_jwt.mapper.UserMapper;
import com.study.two_security_jwt.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByName(String name) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername,name);
        return this.getOne(wrapper);
    }
}
