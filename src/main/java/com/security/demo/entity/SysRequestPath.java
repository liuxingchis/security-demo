package com.security.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_request_path", schema = "security", catalog = "")
@Data
public class SysRequestPath {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "url", nullable = false, length = 64)
    private String url;
    @Column(name = "description", nullable = true, length = 128)
    private String description;
}
