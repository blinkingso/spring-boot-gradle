package com.travelzen.sbg.mapper;

import com.travelzen.sbg.domain.Permission;

import java.util.List;

/**
 * @author andrew
 * @createtime 2017-09-05
 * @IDE INTELLIJ IDEA
 **/
public interface PermissionMapper {

    List<Permission> listAll();

    List<Permission> selectByAdminId(int id);
}
