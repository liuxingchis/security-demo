package com.security.demo.service;

import com.security.demo.entity.SysUser;

public interface SysUserService {

    SysUser selectById(Long id);

    SysUser selectByName(String name);

    SysUser updateSysUser(SysUser sysUser);
}
