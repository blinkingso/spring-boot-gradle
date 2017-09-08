package com.travelzen.sbg.config;

import com.travelzen.sbg.security.CustomUserNameAuthenticationFilter;
import com.travelzen.sbg.security.FilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author andrew
 * @createtime 2017-09-06
 * @IDE INTELLIJ IDEA
 **/
@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserNameAuthenticationFilter customUserNameAuthenticationFilter;

    @Autowired
    private FilterSecurityInterceptor filterSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterBefore(customUserNameAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);

        http.authorizeRequests().antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").and()
                .sessionManagement()
                .and()
                .logout()
                .and()
                .httpBasic();
    }
}
