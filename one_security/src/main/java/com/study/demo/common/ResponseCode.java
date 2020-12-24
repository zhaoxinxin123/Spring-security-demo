package com.study.demo.common;

/**
 * 返回状态码
 *
 * @author LJX
 * @since 1.0.0
 */
public interface ResponseCode {

    /**
     * 成功
     */
    int CODE_OK = 200;



    /**
     * 重复提交
     */
    int CODE_REPEAT_SUBMIT=350;


    /**
     * 提示信息
     */
    int CODE_ALERT_USER = 400;
    /**
     * 用户审核次数超限
     */
    int USER_VALIDATION_MAX = 401;
    /**
     * 用户需重新登录
     */
    int CODE_LOGIN_ERROR = 403;
    /**
     * 用户不存在
     */
    int CODE_USER_NULL = 404;
    /**
     * 用户临时封禁
     */
    int CODE_TEMP_DISABLE = 405;
    /**
     * 用户永久封禁
     */
    int CODE_ALWAYS_DISABLE = 406;
    /**
     * 落地页用户已经存在
     */
    int CODE_USER_EXIST = 410;

    /**
     * 系统异常
     */
    int CODE_SYS_ERROR = 500;

    /**
     * 用户权限不存在
     */
    int CODE_NO_PERMISSION = 3000;









}
