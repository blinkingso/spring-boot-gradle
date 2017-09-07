package com.travelzen.sbg.security;

import com.travelzen.sbg.domain.SysUser;
import com.travelzen.sbg.exception.MyUserLoginException;
import com.travelzen.sbg.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 自定义用户账号密码校验等
 *
 * @author andrew
 * @createtime 2017-09-07
 * @ide Intellij Idea
 **/
@Component
public class CustomUserNameAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String USERNAME = "j_username";
    public static final String PASSWORD = "j_password";

    @Autowired
    private SysUserMapper sysUserMapper;

    public CustomUserNameAuthenticationFilter() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: "
                            + request.getMethod());
        }
        String username = obtainUsername(request).trim();
        String password = obtainPassword(request).trim();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new MyUserLoginException("username or password can not be null.");
        }
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if (sysUser == null)
            throw new UsernameNotFoundException("username does not exists");
        if (!password.equals(sysUser.getPassword())) {
            throw new MyUserLoginException("password wrong.");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, usernamePasswordAuthenticationToken);
        return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object username = request.getParameter(USERNAME);
        return username == null ? "" : username.toString();
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object password = request.getParameter(PASSWORD);
        return password == null ? "" : password.toString();
    }


}
