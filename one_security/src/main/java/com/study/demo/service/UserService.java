package com.study.demo.service;

import com.study.demo.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-22
 */
public interface UserService extends IService<User> {

    User getByUserName(String name);
}
