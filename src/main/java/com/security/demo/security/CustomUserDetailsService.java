package com.security.demo.security;

import com.security.demo.entity.SysPermission;
import com.security.demo.entity.SysUser;
import com.security.demo.service.SysPermissionService;
import com.security.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        SysUser sysUser = sysUserService.selectByName(username);

        // 判断用户是否存在
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //获取该用户所拥有的权限
        List<SysPermission> sysPermissions = sysPermissionService.selectListByUser(sysUser.getId());
        // 声明用户授权
        sysPermissions.forEach(sysPermission -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
            grantedAuthorities.add(grantedAuthority);
        });
        return new User(sysUser.getUserName(), sysUser.getPassword(), sysUser.getEnabled(), sysUser.getNotExpired(), sysUser.getCredentialsNotExpired(), sysUser.getAccountNotLocked(), grantedAuthorities);
    }
}
