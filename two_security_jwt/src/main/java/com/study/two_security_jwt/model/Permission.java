package com.study.two_security_jwt.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_permission")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 权限标识符
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 请求地址
     */
    private String url;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
