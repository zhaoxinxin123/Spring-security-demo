package com.study.two_security_jwt.config;

import com.study.two_security_jwt.handler.JWTAuthenticationFilter;
import com.study.two_security_jwt.handler.JWTAuthorizationFilter;
import com.study.two_security_jwt.exception.MyAccessDeniedHandler;
import com.study.two_security_jwt.exception.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author ZXX
 * @version 1.0
 * @date 2020/12/23 11:36 上午
 * @desc
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     *   http // 关闭跨站请求防护及
     *                 .csrf()
     *                 .disable()
     *                 .authorizeRequests()
     *                 .antMatchers("/user/r/**").authenticated()
     *                 .anyRequest().permitAll()
     *                 //允许表单登录
     *                 .and()
     *                 .formLogin()
     *                 .loginPage("/login-view")
     *                 .loginProcessingUrl("/login")
     *                 //接口必须是post
     *                 .successForwardUrl("/user/login‐success")
     *                 //接口必须是post
     *                 .failureForwardUrl("/user/login‐fail")
     *                 .permitAll()
     *                 .and().exceptionHandling()
     *                 //自定义匿名访问 拦截
     *                 .authenticationEntryPoint(myAuthenticationEntryPoint)
     *                 //授权之后  无权访问拦截
     *                 .accessDeniedHandler(myAccessDeniedHandler);
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/user/r/**").authenticated()
                .anyRequest().permitAll()
                .and()
//                .formLogin()
//                .loginPage("/login-view")
//                .loginProcessingUrl("/login")
//                接口必须是post
//                .successForwardUrl("/user/login‐success")
                //接口必须是post
//                .failureForwardUrl("/user/login‐fail")
//                .permitAll()
                //添加jwt 拦截器
//                .and()
//                .addFilterBefore()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                //不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                //自定义匿名访问 拦截
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                //授权之后  无权访问拦截
                .accessDeniedHandler(myAccessDeniedHandler);

    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }


}
