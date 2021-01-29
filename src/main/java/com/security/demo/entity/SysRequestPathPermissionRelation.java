package com.security.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_request_path_permission_relation", schema = "security", catalog = "")
@Data
public class SysRequestPathPermissionRelation {
    @Id
    @Column(name = "id", nullable = true)
    private Long id;
    @Column(name = "url_id", nullable = true)
    private Long urlId;
    @Column(name = "permission_id", nullable = true)
    private Long permissionId;
}
