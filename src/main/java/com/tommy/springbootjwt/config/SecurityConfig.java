package com.tommy.springbootjwt.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // HttpServletRequest 를 사용하는 요청들에 대한 접근 제한 설정
                .antMatchers("/api/hello").permitAll() // 인증 없이 접근 허용
                .anyRequest().authenticated(); // 나머지 요청들은 모두 인증을 받아야 한다.
    }
}
