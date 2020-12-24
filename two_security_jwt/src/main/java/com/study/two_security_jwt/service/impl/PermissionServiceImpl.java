package com.study.two_security_jwt.service.impl;

import com.study.two_security_jwt.model.Permission;
import com.study.two_security_jwt.mapper.PermissionMapper;
import com.study.two_security_jwt.service.PermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
