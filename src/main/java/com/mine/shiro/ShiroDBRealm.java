package com.mine.shiro;

import com.mine.core.ShiroTool;
import com.mine.model.SystemUser;
import com.mine.service.SystemUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuxianhu on 2018/6/1.
 */
public class ShiroDBRealm extends AuthorizingRealm {

    @Autowired
    SystemUserService systemUserService;

    public void setSystemUserService(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    /***
     * 查询权限
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 用户认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();

        SystemUser systemUser = systemUserService.recieveByUserName(username);

        if(systemUser == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                systemUser.getUsername(), //用户名
                systemUser.getPassword(), //密码
                ByteSource.Util.bytes(systemUser.getSalt()),
                getName()  //realm name
        );

        return authenticationInfo;
    }

    /**
     * 设置认证加密方式
     * 和 生成 credentials (password) 一致
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroTool.algorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroTool.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }

}
