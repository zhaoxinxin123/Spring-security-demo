package com.study.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.demo.model.User;
import com.study.demo.mapper.UserMapper;
import com.study.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getByUserName(String name) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        return this.getOne(wrapper.lambda().eq(User::getUsername,name));
    }
}
