package com.study.two_security_jwt.mapper;

import com.study.two_security_jwt.model.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    @Select("SELECT code from t_permission where id in (select permission_id from t_role_permission where role_id = #{roleId})")
    Set<String> findPermisiionsByRoleId(@Param("roleId") String id);
}
