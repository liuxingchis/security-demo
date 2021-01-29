package com.security.demo.service;

import com.security.demo.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {
    List<SysPermission> selectListByUser(Long userId);

    List<SysPermission> selectListByPath(String url);
}
