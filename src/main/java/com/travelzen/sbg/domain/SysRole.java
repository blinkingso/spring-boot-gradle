package com.travelzen.sbg.domain;

import com.google.common.base.Objects;

/**
 * @author andrew
 * @createtime 2017-09-05
 * @IDE INTELLIJ IDEA
 **/
public class SysRole {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equal(id, sysRole.id) &&
                Objects.equal(name, sysRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }
}
