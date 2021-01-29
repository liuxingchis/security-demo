package com.security.demo.reposittory;

import com.security.demo.entity.SysUser;

public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    SysUser findByUserName(String userName);
}
