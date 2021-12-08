package com.iba.electronicjournalservice.config;

import com.iba.electronicjournalservice.model.user.Roles;
import com.iba.electronicjournalservice.security.jwt.JwtConfigurer;
import com.iba.electronicjournalservice.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/user/**").hasAuthority(Roles.ADMIN.name())
                .antMatchers("/teacher/**", "/student/**").hasAnyAuthority(Roles.TEACHER.name(), Roles.STUDENT.name())
                .antMatchers(HttpMethod.GET, "/group", "/subject", "/mark").hasAnyAuthority(Roles.TEACHER.name(), Roles.STUDENT.name())
                .antMatchers("/mark").hasAuthority(Roles.TEACHER.name())
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
