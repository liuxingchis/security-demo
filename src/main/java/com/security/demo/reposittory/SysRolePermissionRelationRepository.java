package com.security.demo.reposittory;

import com.security.demo.entity.SysRolePermissionRelation;

import java.util.List;

public interface SysRolePermissionRelationRepository extends BaseRepository<SysRolePermissionRelation, Long> {

    List<SysRolePermissionRelation> findAllByRoleIdIsIn(List<Long> roleId);

}
