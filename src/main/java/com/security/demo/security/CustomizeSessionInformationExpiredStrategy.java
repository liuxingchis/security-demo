package com.security.demo.security;

import com.alibaba.fastjson.JSON;
import com.security.demo.common.constant.Constant;
import com.security.demo.common.enums.ResultCode;
import com.security.demo.common.result.JsonResult;
import com.security.demo.common.utils.ResultTool;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("sessionInformationExpiredStrategy")
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException {
        JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
        HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
        httpServletResponse.setContentType(Constant.contentTypeUTF8);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
