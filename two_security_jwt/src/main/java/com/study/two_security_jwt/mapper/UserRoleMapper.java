package com.study.two_security_jwt.mapper;

import com.study.two_security_jwt.model.Role;
import com.study.two_security_jwt.model.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Select("select * from t_role where id = (select role_id from t_user_role where user_id = #{userId})")
    Role selectByUserId(@Param("userId") Long id);
}
