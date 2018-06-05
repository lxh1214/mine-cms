package com.mine.shiro.session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuxianhu on 2018/6/5.
 */
public class MySessionListener implements SessionListener {

    public static Logger logger = LoggerFactory.getLogger(MySessionListener.class);

    public void onStart(Session session) {
        logger.info("onStart : " + session + " , id: " + session.getId());
    }

    public void onStop(Session session) {
        logger.info("onStop : " + session + " , id: " + session.getId());
    }

    public void onExpiration(Session session) {
        logger.info("onExpiration : " + session + " , id: " + session.getId());
    }
}
