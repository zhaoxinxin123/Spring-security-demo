package com.study.two_security_jwt.handler;

import com.study.two_security_jwt.common.JsonResponse;
import com.study.two_security_jwt.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ZXX
 * @version 1.0
 * @date 2020/12/24 11:40 上午
 * @desc  全局异常抛出
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(value = CommonException.class)
    public JsonResponse handleCommonException(CommonException commonException){
        return JsonResponse.error(403,commonException.getMessage());
    }
}
