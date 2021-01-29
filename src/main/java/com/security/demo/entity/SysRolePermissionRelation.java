package com.security.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role_permission_relation", schema = "security", catalog = "")
@Data
public class SysRolePermissionRelation {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "role_id", nullable = true)
    private Long roleId;
    @Column(name = "permission_id", nullable = true)
    private Long permissionId;
}
