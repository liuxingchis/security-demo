package com.security.demo.security;

import com.alibaba.fastjson.JSON;
import com.security.demo.common.constant.Constant;
import com.security.demo.common.enums.ResultCode;
import com.security.demo.common.result.JsonResult;
import com.security.demo.common.utils.ResultTool;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        JsonResult result = ResultTool.fail(ResultCode.NO_PERMISSION);
        httpServletResponse.setContentType(Constant.contentTypeUTF8);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
