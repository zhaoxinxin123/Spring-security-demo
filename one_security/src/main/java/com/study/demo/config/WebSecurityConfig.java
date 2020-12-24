package com.study.demo.config;

import com.study.demo.handler.MyAccessDeniedHandler;
import com.study.demo.handler.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ZXX
 * @version 1.0
 * @date 2020/12/22 3:08 下午
 * @desc
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    /**
     * 配置用户信息服务
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //注入实现userDetailsService接口的自定义实现类
        super.configure(auth);
    }

    /**
     * 配置安全拦截机制
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http // 关闭跨站请求防护及
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/user/r/**").authenticated()
                .anyRequest().permitAll()
                //允许表单登录
                .and()
                .formLogin()
                .loginPage("/login-view")
                .loginProcessingUrl("/login")
                //接口必须是post
                .successForwardUrl("/user/login‐success")
                //接口必须是post
                .failureForwardUrl("/user/login‐fail")
                .permitAll()
                .and().exceptionHandling()
                //自定义匿名访问 拦截
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                //授权之后  无权访问拦截
                .accessDeniedHandler(myAccessDeniedHandler);
//         .sessionManagement()  无状态交互不使用session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    /**
     * 配置密码加密规则
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
