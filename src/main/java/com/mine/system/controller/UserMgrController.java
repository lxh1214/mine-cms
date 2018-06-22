package com.mine.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by angle on 2018/6/1.
 */
@Controller
@RequestMapping("/umgr")
public class UserMgrController {

    @RequestMapping("/hello")
    public String hello() {
        return "success";
    }

    @RequestMapping("/index")
    public String index() {
        return "success";
    }

}
