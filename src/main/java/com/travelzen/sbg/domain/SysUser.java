package com.travelzen.sbg.domain;

import com.google.common.base.Objects;
import org.apache.tomcat.jni.User;

import java.util.List;

/**
 * @author andrew
 * @createtime 2017-09-05
 * @IDE INTELLIJ IDEA
 **/
public class SysUser {

    private Integer id;
    private String username;
    private String password;

    private List<SysRole> roles;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return Objects.equal(id, sysUser.id) &&
                Objects.equal(username, sysUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, username);
    }
}
