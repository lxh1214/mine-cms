package com.mine.system.controller;

import com.mine.common.BaseController;
import com.mine.core.ShiroTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liuxianhu on 2018/6/3.
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {

        if (ShiroTool.isAuthenticated() || ShiroTool.isUser()) {
            return REDIRECT + "/";
        } else {
            return "login";
        }
    }
}
