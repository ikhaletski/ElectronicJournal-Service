package com.iba.electronicjournalservice.config;

import com.iba.electronicjournalservice.security.jwt.JwtTokenFilter;
import com.iba.electronicjournalservice.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@AllArgsConstructor
public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
