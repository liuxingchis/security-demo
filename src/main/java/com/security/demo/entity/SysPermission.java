package com.security.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_permission", schema = "security", catalog = "")
@Data
public class SysPermission {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "permission_code", nullable = true, length = 32)
    private String permissionCode;
    @Column(name = "permission_name", nullable = true, length = 32)
    private String permissionName;
}
