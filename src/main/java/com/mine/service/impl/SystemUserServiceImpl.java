package com.mine.service.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    /**
     * username 是唯一的
     * @param userName
     * @return
     */
    public SystemUser recieveByUserName(String userName) {

        if(userName == null || userName.trim().equals("")) {
            return null;
        }

        SystemUserExample systemUserExample = new SystemUserExample();

        systemUserExample.createCriteria().andUsernameEqualTo(userName);

        List<SystemUser> users = systemUserMapper.selectByExample(systemUserExample);

        if(users != null && users.size() == 1) {
            return users.get(0);
        }
        // todo 数据错误
        return null;

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

    /**
     *  通过分页插件
     * @param pageNum 当前页数
     * @param pageSize 每页大小
     * @param systemUserExample
     * @return
     */
    public Page<SystemUser> pageBySystemUserExample(Integer pageNum, Integer pageSize, final SystemUserExample systemUserExample) {

        return PageHelper.startPage(pageNum, pageSize).doSelectPage(new ISelect() {
            public void doSelect() {
                systemUserMapper.selectByExample(systemUserExample);
            }
        });

    }


    public int deleteSystemUser(SystemUserExample systemUserExample) {
        return systemUserMapper.deleteByExample(systemUserExample);
    }

    public SystemUser updateSystemUser(SystemUser systemUser, SystemUserExample systemUserExample) {
        systemUserMapper.updateByExampleSelective(systemUser, systemUserExample);
        return systemUser;
    }


}
