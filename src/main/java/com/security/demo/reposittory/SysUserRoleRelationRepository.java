package com.security.demo.reposittory;

import com.security.demo.entity.SysUserRoleRelation;

import java.util.List;

public interface SysUserRoleRelationRepository extends BaseRepository<SysUserRoleRelation, Long> {

    List<SysUserRoleRelation> findAllByUserId(Long userId);
}
