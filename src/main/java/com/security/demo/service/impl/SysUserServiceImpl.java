package com.security.demo.service.impl;

import com.security.demo.entity.SysUser;
import com.security.demo.reposittory.SysUserRepository;
import com.security.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser selectById(Long id) {
        return sysUserRepository.findById(id).get();
    }

    @Override
    public SysUser selectByName(String userName) {
        return sysUserRepository.findByUserName(userName);
    }

    @Override
    public SysUser updateSysUser(SysUser sysUser) {
        return sysUserRepository.save(sysUser);
    }
}
