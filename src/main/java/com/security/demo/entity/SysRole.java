package com.security.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role", schema = "security", catalog = "")
@Data
public class SysRole {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "role_name", nullable = true, length = 32)
    private String roleName;
    @Column(name = "role_description", nullable = true, length = 64)
    private String roleDescription;
}
