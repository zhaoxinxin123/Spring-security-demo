package com.study.two_security_jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.two_security_jwt.model.Role;
import com.study.two_security_jwt.model.UserRole;
import com.study.two_security_jwt.mapper.UserRoleMapper;
import com.study.two_security_jwt.service.UserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public Role getOneByUserId(Long id) {

        return this.baseMapper.selectByUserId(id);
    }
}
