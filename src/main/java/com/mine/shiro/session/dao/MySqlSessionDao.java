package com.mine.shiro.session.dao;

import com.mine.core.Servlets;
import com.mine.dao.SystemSessionMapper;
import com.mine.model.SystemSession;
import com.mine.shiro.session.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by liuxianhu on 2018/6/5.
 */
@Service
public class MySqlSessionDao extends CachingSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(MySqlSessionDao.class);

    @Autowired
    SystemSessionMapper systemSessionMapper;

    /**
     * 排除的更新 session Url
     */
    private String[] excludeUrls;


    public void setExcludeUrls(String[] excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    protected void doUpdate(Session session) {

        logger.info("doUpdate start.");

        // 过期后不更新
        if(session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }

        String uri = Servlets.getRequest().getRequestURI().toLowerCase();

        for (String str : excludeUrls) {
            if (uri.toLowerCase().matches(str.toLowerCase().trim())) {
                logger.trace("doUpdate session excludeUrl:"+ str + " matches sucess! ");
                return;
            }
        }

        SystemSession systemSession = new SystemSession();
        systemSession.setId(session.getId().toString());
        systemSession.setSession(SerializableUtils.serialize(session));

        systemSessionMapper.updateByPrimaryKey(systemSession);

        logger.info("doUpdate end.");
    }

    protected void doDelete(Session session) {
        logger.info("doDelete start.");
        systemSessionMapper.deleteByPrimaryKey(session.getId().toString());
        logger.info("doDelete end.");
    }

    protected Serializable doCreate(Session session) {
        logger.info("doCreate start.");
        Serializable sessionId = this.generateSessionId(session);
        logger.info("doCreate sessionId  " + sessionId);
        assignSessionId(session, sessionId);
        SystemSession systemSession = new SystemSession();
        systemSession.setId(sessionId.toString());
        systemSession.setSession(SerializableUtils.serialize(session));
        systemSessionMapper.insert(systemSession);
        logger.info("doCreate session :" + session);
        logger.info("doCreate systemSession :" + systemSession);
        logger.info("doCreate end.");
        return sessionId;
    }

    protected Session doReadSession(Serializable sessionId) {
        logger.info("doReadSession start.");
        SystemSession systemSession = systemSessionMapper.selectByPrimaryKey(sessionId.toString());

        logger.info("doReadSession systemSession :" + systemSession);

        if(systemSession == null) {
            logger.info("doReadSession end.");
            return null;
        }

        Session session = SerializableUtils.deserialize(systemSession.getSession());
        logger.info("session :" + session);
        logger.info("doReadSession end.");
        return session;
    }
}
