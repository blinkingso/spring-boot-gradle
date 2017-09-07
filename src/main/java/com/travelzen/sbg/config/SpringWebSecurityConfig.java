package com.travelzen.sbg.config;

import com.travelzen.sbg.exception.MyUserLoginException;
import com.travelzen.sbg.security.CustomUserNameAuthenticationFilter;
import com.travelzen.sbg.security.FilterSecurityInterceptor;
import com.travelzen.sbg.security.LoginAuthenticationSuccessHandler;
import com.travelzen.sbg.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author andrew
 * @createtime 2017-09-06
 * @IDE INTELLIJ IDEA
 **/
@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    @Autowired
    private CustomUserNameAuthenticationFilter customUserNameAuthenticationFilter;

    //@Autowired
    //private FilterSecurityInterceptor filterSecurityInterceptor;

    // 注册自定义的UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    // UserDetailsService验证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //failedAuthenticationHandler
        ExceptionMappingAuthenticationFailureHandler exceptionMappingAuthenticationFailureHandler = new ExceptionMappingAuthenticationFailureHandler();
        Map<String, String> exceptionMap = new HashMap<>();
        exceptionMap.put(MyUserLoginException.class.getName(), "/login?msg=passwordFailed");
        exceptionMappingAuthenticationFailureHandler.setExceptionMappings(exceptionMap);
        http.authorizeRequests().antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").successHandler(loginAuthenticationSuccessHandler).failureHandler(exceptionMappingAuthenticationFailureHandler).permitAll() // 登录页面用户任意访问
                .and().logout().permitAll(); // 注销行为任意访问
        http.addFilterBefore(customUserNameAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);
    }
}
