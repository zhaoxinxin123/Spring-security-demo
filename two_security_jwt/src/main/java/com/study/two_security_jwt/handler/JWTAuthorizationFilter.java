package com.study.two_security_jwt.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.two_security_jwt.common.JsonResponse;
import com.study.two_security_jwt.common.utils.JwtTokenUtils;
import com.study.two_security_jwt.exception.CommonException;
import com.study.two_security_jwt.exception.TokenIsExpiredException;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author ZXX
 * @version 1.0
 * @date 2020/12/23 1:22 下午
 * @desc
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        System.out.println("进入JWTAuthorizationFilter");
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        } catch (TokenIsExpiredException e) {
            //返回json形式的错误信息
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String reason = "统一处理，原因：" + e.getMessage();
            response.getWriter().write(JSON.toJSON(JsonResponse.error(HttpServletResponse.SC_FORBIDDEN,"token超时了")).toString());
            response.getWriter().flush();
            return;
        }
        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws TokenIsExpiredException {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtTokenUtils.isExpiration(token);
        if (expiration) {
            throw new TokenIsExpiredException("token超时了");
        } else {
            String username = JwtTokenUtils.getUsername(token);
            if (username != null) {
                Collection<SimpleGrantedAuthority> authorities =new ArrayList<>();
                List<String> userPermission = JwtTokenUtils.getUserPermission(token);
                String role  = JwtTokenUtils.getUserRole(token);
                for (String s : userPermission) {
                    authorities.add(new SimpleGrantedAuthority(s));
                }
                authorities.add(new SimpleGrantedAuthority(role));
                return new UsernamePasswordAuthenticationToken(username, null,
                        authorities);

            }
        }
        return null;
    }
}
