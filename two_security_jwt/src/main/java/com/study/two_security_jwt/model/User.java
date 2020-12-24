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
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long id;

    private String username;

    private String password;

    /**
     * 用户姓名
     */
    private String fullname;

    /**
     * 手机号
     */
    private String mobile;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
