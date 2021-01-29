package com.security.demo.controller;

import com.security.demo.common.result.JsonResult;
import com.security.demo.common.utils.ResultTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @PostMapping("/test")
    @ResponseBody
    public JsonResult test() {
        return ResultTool.success("test");
    }

    @PostMapping("/test2")
    @ResponseBody
    public JsonResult test2() {
        return ResultTool.success("test2");
    }


    @PostMapping("/no-auth/test")
    @ResponseBody
    public JsonResult auth() {
        return ResultTool.success("test");
    }
}
