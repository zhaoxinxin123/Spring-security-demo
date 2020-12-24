package com.study.two_security_jwt.service;

import com.study.two_security_jwt.model.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
public interface RolePermissionService extends IService<RolePermission> {

    Set<String> findByRoleId(String id);
}
