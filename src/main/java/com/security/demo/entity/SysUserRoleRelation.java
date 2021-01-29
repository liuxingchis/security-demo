package com.security.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user_role_relation", schema = "security", catalog = "")
@Data
public class SysUserRoleRelation {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id", nullable = true)
    private Long userId;
    @Column(name = "role_id", nullable = true)
    private Long roleId;
}
