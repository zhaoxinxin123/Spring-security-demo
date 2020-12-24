package com.study.two_security_jwt.service.impl;

import com.study.two_security_jwt.model.RolePermission;
import com.study.two_security_jwt.mapper.RolePermissionMapper;
import com.study.two_security_jwt.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public Set<String> findByRoleId(String id) {
        return this.baseMapper.findPermisiionsByRoleId(id);
    }
}
