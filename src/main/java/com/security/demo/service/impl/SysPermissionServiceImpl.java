package com.security.demo.service.impl;

import com.security.demo.entity.*;
import com.security.demo.reposittory.*;
import com.security.demo.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    SysUserRoleRelationRepository sysUserRoleRelationRepository;
    @Autowired
    SysRoleRepository sysRoleRepository;
    @Autowired
    SysRolePermissionRelationRepository sysRolePermissionRelationRepository;
    @Autowired
    SysPermissionRepository sysPermissionRepository;
    @Autowired
    SysRequestPathRepository sysRequestPathRepository;
    @Autowired
    SysRequestPathPermissionRelationRepository sysRequestPathPermissionRelationRepository;

    @Override
    public List<SysPermission> selectListByUser(Long userId) {
//        List<Long> sysRoleIdList = sysUserRoleRelationRepository.findAllByUserId(userId).stream().map(SysUserRoleRelation::getRoleId).collect(Collectors.toList());
//        List<Long> sysPermissionIdList = sysRolePermissionRelationRepository.findAllByRoleIdIsIn(sysRoleIdList).stream().map(SysRolePermissionRelation::getPermissionId).collect(Collectors.toList());
//        return sysPermissionRepository.findAllById(sysPermissionIdList);
        return sysPermissionRepository.selectListByUserId(userId);
    }

    @Override
    public List<SysPermission> selectListByPath(String url) {
        SysRequestPath sysRequestPath = sysRequestPathRepository.findByUrl(url);
        if (sysRequestPath == null) {
            return null;
        }
        List<Long> sysPermissionIdList = sysRequestPathPermissionRelationRepository.findAllByUrlId(sysRequestPath.getId()).stream().map(SysRequestPathPermissionRelation::getPermissionId).collect(Collectors.toList());
        return sysPermissionRepository.findAllById(sysPermissionIdList);
    }
}
