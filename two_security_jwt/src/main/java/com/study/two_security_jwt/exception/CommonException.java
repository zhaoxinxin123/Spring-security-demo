package com.study.two_security_jwt.exception;

/**
 * @author ZXX
 * @version 1.0
 * @date 2020/12/24 11:44 上午
 * @desc  通用异常抛出
 */
public class CommonException extends RuntimeException {
    public CommonException(String message) {
        super(message);
    }
}
