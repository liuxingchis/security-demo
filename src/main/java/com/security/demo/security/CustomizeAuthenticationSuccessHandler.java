package com.security.demo.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.security.demo.common.constant.Constant;
import com.security.demo.common.result.JsonResult;
import com.security.demo.common.utils.JwtTokenProvider;
import com.security.demo.common.utils.ResultTool;
import com.security.demo.entity.SysUser;
import com.security.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component("authenticationSuccessHandler")
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间、更新人、更新时间等字段
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.selectByName(userDetails.getUsername());
        sysUser.setLastLoginTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateUser(sysUser.getId());
        sysUserService.updateSysUser(sysUser);

        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展

        //生成token
        String token = jwtTokenProvider.createJwtToken(sysUser.getUserName());
        JSONObject data = new JSONObject();
        data.put(Constant.tokenHeader, token);
        //返回json数据
        JsonResult result = ResultTool.success(data);
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType(Constant.contentTypeUTF8);
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
