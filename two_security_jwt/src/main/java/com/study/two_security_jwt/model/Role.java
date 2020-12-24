package com.study.two_security_jwt.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
@TableName("t_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String roleName;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
