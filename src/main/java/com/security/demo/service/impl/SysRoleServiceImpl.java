package com.security.demo.service.impl;

import com.security.demo.entity.SysRole;
import com.security.demo.reposittory.SysRoleRepository;
import com.security.demo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Override
    public SysRole selectById(Long id) {
        return sysRoleRepository.findById(id).get();
    }
}
