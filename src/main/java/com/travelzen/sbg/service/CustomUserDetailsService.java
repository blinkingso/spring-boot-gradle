package com.travelzen.sbg.service;

import com.travelzen.sbg.domain.SysRole;
import com.travelzen.sbg.domain.SysUser;
import com.travelzen.sbg.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew
 * @createtime 2017-09-06
 * @IDE INTELLIJ IDEA
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if (sysUser == null)
            throw new UsernameNotFoundException("用户名不存在");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (CollectionUtils.isEmpty(sysUser.getRoles()))
            throw new UsernameNotFoundException("角色不存在");
        // 添加用户的权限
        for (SysRole role : sysUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        UserDetails userDetails = new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
        return userDetails;
    }
}
