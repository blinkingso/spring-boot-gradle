package com.travelzen.sbg.service;

import com.travelzen.sbg.domain.Permission;
import com.travelzen.sbg.domain.SysUser;
import com.travelzen.sbg.exception.PermissionNotFoundException;
import com.travelzen.sbg.mapper.PermissionMapper;
import com.travelzen.sbg.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
 * 从数据库中查询当前用户的密码并加载权限信息
 *
 * @author andrew
 * @createtime 2017-09-06
 * @IDE INTELLIJ IDEA
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if (sysUser == null)
            throw new UsernameNotFoundException("用户名不存在");
        if (CollectionUtils.isEmpty(sysUser.getRoles()))
            throw new UsernameNotFoundException("角色不存在");
        // 添加用户的权限
        List<Permission> permissions = permissionMapper.selectByAdminId(sysUser.getId());
        if (CollectionUtils.isEmpty(permissions))
            throw new PermissionNotFoundException("权限配置为空");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permission permission : permissions) {
            if (permission != null && permission.getName() != null) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getName());
                grantedAuthorities.add(authority);
            }
        }
        UserDetails userDetails = new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
        return userDetails;
    }
}
