package com.security.demo.reposittory;

import com.security.demo.entity.SysRequestPath;

public interface SysRequestPathRepository extends BaseRepository<SysRequestPath, Long>{

    SysRequestPath findByUrl(String url);
}
