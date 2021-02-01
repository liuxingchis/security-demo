package com.security.demo.reposittory;

import com.security.demo.entity.SysPermission;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysPermissionRepository extends BaseRepository<SysPermission, Long> {

    @Query(value = "select sysPermission from SysPermission sysPermission where sysPermission.id in (select sysRolePermissionRelation.permissionId  from SysRolePermissionRelation sysRolePermissionRelation where sysRolePermissionRelation.roleId in (select sysUserRoleRelation.roleId from SysUserRoleRelation sysUserRoleRelation where sysUserRoleRelation.userId = ?1))")
    List<SysPermission> selectListByUserId(Long userId);
}
