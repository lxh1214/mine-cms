package com.mine.service.impl;

import com.mine.dao.SystemUserMapper;
import com.mine.model.SystemUser;
import com.mine.model.SystemUserExample;
import com.mine.service.SystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuxianhu on 2018/5/29.
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final static Logger logger = LoggerFactory.getLogger(SystemUserServiceImpl.class);

    @Autowired
    SystemUserMapper systemUserMapper;

    public SystemUser getSystemUser(Long userId) {
        logger.info("userId :"+ userId);
        return systemUserMapper.selectByPrimaryKey(userId);
    }

    public SystemUser saveSystemUser(SystemUser systemUser) {
        logger.info("save systemUser start : " + systemUser);
        systemUserMapper.insertSelective(systemUser);
        logger.info("save systemUser  end : id " + systemUser.getId() + " ," + systemUser);
        return systemUser;
    }

    public List<SystemUser> findSysteUser(SystemUserExample systemUserExample) {
        return systemUserMapper.selectByExample(systemUserExample);
    }


    public int deleteSystemUser(SystemUserExample systemUserExample) {
        return systemUserMapper.deleteByExample(systemUserExample);
    }

    public SystemUser updateSystemUser(SystemUser systemUser, SystemUserExample systemUserExample) {
        systemUserMapper.updateByExampleSelective(systemUser, systemUserExample);
        return systemUser;
    }
}
