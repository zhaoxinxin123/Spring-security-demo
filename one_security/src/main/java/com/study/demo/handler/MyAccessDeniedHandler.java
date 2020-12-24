package com.study.demo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.demo.common.JsonResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ZXX
 * @version 1.0
 * @date 2020/12/22 4:59 下午
 * @desc  已授权但是没有权限访问
 */

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {

        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String errorMsg = objectMapper.writeValueAsString(JsonResponse.error(403,"孩子你跑错厕所了"));
        out.write(errorMsg);
        out.flush();
        out.close();
    }
}