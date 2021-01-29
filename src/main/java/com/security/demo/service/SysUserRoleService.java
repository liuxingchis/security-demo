package com.security.demo.service;

import com.security.demo.entity.SysUserRoleRelation;

import java.util.List;

public interface SysUserRoleService {

    List<SysUserRoleRelation> listByUserId(Long userId);
}
