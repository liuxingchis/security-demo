package com.security.demo.service.impl;

import com.security.demo.entity.SysUserRoleRelation;
import com.security.demo.reposittory.SysUserRoleRelationRepository;
import com.security.demo.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    SysUserRoleRelationRepository sysUserRoleRelationRepository;

    @Override
    public List<SysUserRoleRelation> listByUserId(Long userId) {
        return sysUserRoleRelationRepository.findAllByUserId(userId);
    }
}
