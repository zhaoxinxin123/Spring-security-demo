package com.study.two_security_jwt.service;

import com.study.two_security_jwt.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
public interface UserService extends IService<User> {

    User findByName(String name);
}
