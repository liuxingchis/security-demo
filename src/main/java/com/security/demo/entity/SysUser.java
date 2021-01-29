package com.security.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sys_user", schema = "security", catalog = "")
@Data
public class SysUser {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 32)
    private String name;
    @Column(name = "user_name", nullable = false, length = 32)
    private String userName;
    @Column(name = "password", nullable = true, length = 64)
    private String password;
    @Column(name = "last_login_time", nullable = true)
    private Date lastLoginTime;
    @Column(name = "enabled", nullable = true)
    private Boolean enabled;
    @Column(name = "not_expired", nullable = true)
    private Boolean notExpired;
    @Column(name = "account_not_locked", nullable = true)
    private Boolean accountNotLocked;
    @Column(name = "credentials_not_expired", nullable = true)
    private Boolean credentialsNotExpired;
    @Column(name = "create_time", nullable = true)
    private Date createTime;
    @Column(name = "update_time", nullable = true)
    private Date updateTime;
    @Column(name = "create_user", nullable = true)
    private Long createUser;
    @Column(name = "update_user", nullable = true)
    private Long updateUser;
}
