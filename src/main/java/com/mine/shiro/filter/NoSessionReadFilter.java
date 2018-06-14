package com.mine.shiro.filter;

import com.mine.core.Servlets;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by liuxianhu on 2018/6/13.
 * 匹配的url默认设置 request 属性 *. SESSION_READ_ENABLED 为false
 * 为了减少 session read
 *
 */
public class NoSessionReadFilter extends PathMatchingFilter {

    public static final String SESSION_READ_ENABLED = NoSessionReadFilter.class.getName() + ".SESSION_READ_ENABLED";

    private static final Logger logger = LoggerFactory.getLogger(NoSessionReadFilter.class);

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        logger.info("NoSessionReadFilter uri : " + Servlets.getRequest().getRequestURI());
        request.setAttribute(SESSION_READ_ENABLED, Boolean.FALSE);
        return true;
    }
}
