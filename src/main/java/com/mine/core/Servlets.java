package com.mine.core;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuxianhu on 2018/6/7.
 */
public class Servlets {


    /**
     *  RequestContextHolder
     *  需要
     *  web.xml
     *   <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
     *   支持
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
