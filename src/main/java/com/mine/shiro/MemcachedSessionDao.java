package com.mine.shiro;

import com.mine.shiro.session.CustomAbstractSessionDAO;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
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

    private int expireTime;

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    protected Serializable doCreate(Session session) {

        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);

        logger.info("session timeout : " + session.getTimeout());

        try {
            memcachedClient.set(sessionId.toString(), expireTime, session);
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
        try {
            memcachedClient.set(session.getId().toString(), expireTime, session);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Session session) {
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
        return Collections.emptyList();
    }


}
