package com.mine.shiro.session.dao;

import com.mine.core.Servlets;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

/**
 * 继承 CachingSessionDAO
 * 主要用到 update,delete,  session
 */
public class CustomCachingSessionDAO extends CachingSessionDAO {

    /**
     * 排除的更新 session Url
     */
    private String[] excludeUrls = new String[]{};


    public void setExcludeUrls(String[] excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    protected boolean excludeUpdate(Session session) {

        // 过期后不更新
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return true;
        }

        String uri = obtainUri();
        /**
         * 匹配url规则不更新
         */
        for (String str : excludeUrls) {
            if (uri.toLowerCase().matches(str.toLowerCase().trim())) {
                return true;
            }
        }

        return false;

    }

    /**
     * 获取 request uri 可重写
     * @return
     */
    protected String obtainUri() {
        return Servlets.getRequest().getRequestURI().toLowerCase();
    }

    protected void doUpdate(Session session) {

    }

    protected void doDelete(Session session) {

    }

    protected Serializable doCreate(Session session) {
        return null;
    }

    protected Session doReadSession(Serializable sessionId) {
        return null;
    }
}
