package com.mine.shiro.session.dao;

import com.mine.core.Servlets;
import com.mine.shiro.filter.NoSessionReadFilter;
import com.mine.shiro.session.dao.CustomAbstractSessionDAO;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

/**
 * Created by liuxianhu on 2018/6/4.
 *
 * memcached 管理 session
 *
 * 这里使用 memcached 自身的 过期时间处理
 */
@Service
public class MemcachedSessionDao extends CustomAbstractSessionDAO {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemcachedClient memcachedClient;

    public static final int DEFAULT_SESSION_TIMEOUT = 60 * 60;

    /**
     * 设置默认超时时间
     */
    private int sessionExpireTime = DEFAULT_SESSION_TIMEOUT;

    public void setSessionExpireTime(int sessionExpireTime) {
        this.sessionExpireTime = sessionExpireTime;
    }

    protected Serializable doCreate(Session session) {

        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);

        logger.info("doCreate session timeout : {}, doCreate uri : {}, sessionId.toString {}", session.getTimeout(),obtainUri(), sessionId.toString());
        try {
            memcachedClient.set(sessionId.toString(), sessionExpireTime , session);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return session.getId();
    }

    protected Session doReadSession(Serializable sessionId) {
        Session session = null;

        try {
            session = memcachedClient.get(sessionId.toString());
            logger.info("doReadSession uri : {}, sessionId : {}, session : {}" , new Object[]{obtainUri(), sessionId, session} );
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

        return session;
    }


    protected void doUpdate(Session session) {
        logger.info("doUpdate uri :" + obtainUri());
        try {
            memcachedClient.set(session.getId().toString(), sessionExpireTime, session);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Session session) {
        logger.info("delete uri :" + obtainUri());
        try {
            memcachedClient.delete(session.getId().toString());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    /***
     * 获取所有sessions
     * 因为使用 memcached 无法获取全部的 sessions
     * 再一个 不利用 sessionManager 中的 schedule 调度中心来 定时轮询
     *  session 过期由 memcached 服务器自己维护
     * @return
     */
    public Collection<Session> getActiveSessions() {
        logger.info("getActiveSessions");
        return Collections.emptyList();
    }


}
