package com.study.demo.mapper;

import com.study.demo.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
