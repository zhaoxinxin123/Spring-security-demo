package com.study.two_security_jwt.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.two_security_jwt.common.JsonResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ZXX
 * @version 1.0
 * @date 2020/12/22 4:55 下午
 * @desc  匿名访问时
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        String errorMsg = objectMapper.writeValueAsString(JsonResponse.error(403,"孩子，登录后再来试试"));
        out.write(errorMsg);
        out.flush();
        out.close();
    }
}
