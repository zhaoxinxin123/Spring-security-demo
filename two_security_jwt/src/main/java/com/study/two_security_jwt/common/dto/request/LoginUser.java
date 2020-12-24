package com.study.two_security_jwt.common.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZXX
 * @version 1.0
 * @date 2020/12/23 1:55 下午
 * @desc
 */
@Data
public class LoginUser implements Serializable {
    private String username;

    private String password;
}
