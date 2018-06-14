package com.mine.shiro.session.manager;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liuxianhu on 2018/6/8.
 */
public class CustomWebSessionManager extends DefaultWebSessionManager{

    protected PatternMatcher pathMatcher = new AntPathMatcher();

    private static final Logger log = LoggerFactory.getLogger(DefaultWebSessionManager.class);

    private Set<String> excludeNoReadSessionPaths = new LinkedHashSet<String>();

    public void setExcludeNoReadSessionPaths(Set<String> excludeNoReadSessionPaths) {
        this.excludeNoReadSessionPaths = excludeNoReadSessionPaths;
    }

    /**
     * 1. 避免多次 调用
     * sessionDAO.readSession
     * 通过 request attribute 设置属性
     * 让在同一个 request 请求中 只请求一次
     * 2. 通过 excludeNoReadSessionPaths 排除 固定uri 不读取 readSession
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);

        ServletRequest request = null;


        if(sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();

            if(excludeNoReadSessionPaths.size() != 0 && request instanceof HttpServletRequest) {
                String uri =  ((HttpServletRequest) request).getRequestURI();
                Iterator<String> noReadPathIt = excludeNoReadSessionPaths.iterator();
                while(noReadPathIt.hasNext()) {
                    if(pathsMatch(noReadPathIt.next(), uri)) {
                        return null;
                    }
                }
            }

        }

        if(request != null && sessionId != null) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
                return (Session) sessionObj;
            }
        }

        Session s = super.retrieveSession(sessionKey);

        if(s != null) {
            request.setAttribute(sessionId.toString(), s);
        }
        return s;
    }

    protected boolean pathsMatch(String pattern, String path) {
        return this.pathMatcher.matches(pattern, path);
    }
}
