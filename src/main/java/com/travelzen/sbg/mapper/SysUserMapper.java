package com.travelzen.sbg.mapper;

import com.travelzen.sbg.domain.SysUser;

/**
 * @author andrew
 * @createtime 2017-09-06
 * @IDE INTELLIJ IDEA
 **/
public interface SysUserMapper {
    /**
     * 通过用户名获取用户详情
     *
     * @param username
     * @return
     */
    SysUser selectByUsername(String username);
}
