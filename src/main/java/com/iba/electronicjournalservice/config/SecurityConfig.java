package com.iba.electronicjournalservice.config;

import com.iba.electronicjournalservice.model.user.Roles;
import com.iba.electronicjournalservice.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;


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
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().configurationSource(request -> corsConfiguration)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/user/**", "/group/**").hasAuthority(Roles.ADMIN.name())
                .antMatchers("/teacher/**", "/student/**", "/mark/**", "/subject/**").hasAnyAuthority(Roles.TEACHER.name(), Roles.STUDENT.name(), Roles.ADMIN.name())
                .antMatchers( "/profile/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfig(jwtTokenProvider));
    }
}
