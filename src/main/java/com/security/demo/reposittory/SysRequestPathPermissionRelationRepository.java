package com.security.demo.reposittory;

import com.security.demo.entity.SysRequestPathPermissionRelation;

import java.util.List;

public interface SysRequestPathPermissionRelationRepository extends BaseRepository<SysRequestPathPermissionRelation, Long> {
    List<SysRequestPathPermissionRelation> findAllByUrlId(Long urlId);
}
