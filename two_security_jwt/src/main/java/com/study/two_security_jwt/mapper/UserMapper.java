package com.study.two_security_jwt.mapper;

import com.study.two_security_jwt.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
