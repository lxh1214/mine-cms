package com.mine.service;

import com.mine.model.SystemUser;
import com.mine.model.SystemUserExample;

import java.util.List;

/**
 * Created by liuxianhu on 2018/5/29.
 */


public interface SystemUserService {


    SystemUser getSystemUser(Long userId);

    SystemUser saveSystemUser(SystemUser systemUser);

    List<SystemUser> findSysteUser(SystemUserExample systemUserExample);

    int deleteSystemUser(SystemUserExample systemUserExample);

    SystemUser updateSystemUser(SystemUser systemUser, SystemUserExample systemUserExample);

}