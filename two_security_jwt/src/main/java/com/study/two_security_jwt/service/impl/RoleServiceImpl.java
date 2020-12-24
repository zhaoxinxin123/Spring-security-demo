package com.study.two_security_jwt.service.impl;

import com.study.two_security_jwt.model.Role;
import com.study.two_security_jwt.mapper.RoleMapper;
import com.study.two_security_jwt.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
