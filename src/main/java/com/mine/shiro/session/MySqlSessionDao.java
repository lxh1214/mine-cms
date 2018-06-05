package com.mine.shiro.session;

import com.mine.dao.SystemSessionMapper;
import com.mine.model.SystemSession;
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

    protected void doUpdate(Session session) {
        logger.info("doUpdate start.");
        // 过期后不更新
        if(session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }

        Serializable sessionId = this.generateSessionId(session);
        assignSessionId(session, sessionId);
        SystemSession systemSession = new SystemSession();
        systemSession.setId(sessionId.toString());
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
        logger.info("sessionId  " + sessionId);
        assignSessionId(session, sessionId);
        SystemSession systemSession = new SystemSession();
        systemSession.setId(sessionId.toString());
        systemSession.setSession(SerializableUtils.serialize(session));
        systemSessionMapper.insert(systemSession);
        logger.info("session :" + session);
        logger.info("systemSession :" + systemSession);
        logger.info("doCreate end.");
        return sessionId;
    }

    protected Session doReadSession(Serializable sessionId) {
        logger.info("doReadSession start.");
        SystemSession systemSession = systemSessionMapper.selectByPrimaryKey(sessionId.toString());

        logger.info("systemSession :" + systemSession);

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
