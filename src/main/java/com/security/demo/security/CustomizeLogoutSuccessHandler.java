package com.security.demo.security;

import com.alibaba.fastjson.JSON;
import com.security.demo.common.constant.Constant;
import com.security.demo.common.result.JsonResult;
import com.security.demo.common.utils.JwtTokenProvider;
import com.security.demo.common.utils.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("logoutSuccessHandler")
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        jwtTokenProvider.deleteToken(httpServletRequest.getHeader(Constant.tokenHeader));

        JsonResult result = ResultTool.success();
        httpServletResponse.setContentType(Constant.contentTypeUTF8);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
