package com.security.demo.security;

import com.alibaba.fastjson.JSON;
import com.security.demo.common.constant.Constant;
import com.security.demo.common.enums.ResultCode;
import com.security.demo.common.result.JsonResult;
import com.security.demo.common.utils.ResultTool;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationEntryPoint")
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        httpServletResponse.setContentType(Constant.contentTypeUTF8);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
