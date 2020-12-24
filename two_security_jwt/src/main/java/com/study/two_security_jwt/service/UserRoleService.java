package com.study.two_security_jwt.service;

import com.study.two_security_jwt.model.Role;
import com.study.two_security_jwt.model.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
public interface UserRoleService extends IService<UserRole> {

    Role getOneByUserId(Long id);
}
