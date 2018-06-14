package com.mine.shiro.session.dao;

import com.mine.core.Servlets;
import com.mine.shiro.filter.NoSessionReadFilter;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

/**
 * Created by liuxianhu on 2018/6/7.
 *
 * 增加一个 excludeUpdate session
 * 满足 excludeUrls的不更新 session
 * excludeUpdate 只适用于 spring  web 环境
 *
 *
 */
public abstract class CustomAbstractSessionDAO extends AbstractSessionDAO {

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
     * NoSessionReadFilter
     *
     * @return
     */
    protected boolean sessionReadEnabled() {
        Boolean enable = (Boolean) Servlets.getRequest().getAttribute(NoSessionReadFilter.SESSION_READ_ENABLED);
        return enable != null ? enable : true;
    }
    /**
     * 获取 request uri 可重写
     * @return
     */
    protected String obtainUri() {
        return Servlets.getRequest().getRequestURI().toLowerCase();
    }

    public void update(Session session) throws UnknownSessionException {

        boolean isExclude = excludeUpdate(session);

        if(!isExclude) {
            doUpdate(session);
        }

    }

    /**
     * 需要具体的实现
     * @param session
     */
    protected abstract void doUpdate(Session session);
}
