package com.mine.core;

import com.mine.model.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

/**
 * Created by angle on 2018/6/1.
 */
public abstract class ShiroTool {

    public static final String algorithmName = "MD5";

    public static final int hashIterations = 5;

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /***
     * 获取随机slat
     * @return
     */
    public static String getRandomSlat() {
        return randomNumberGenerator.nextBytes().toHex();
    }

    /***
     *  以md5的形式加密后的 hash 值 用于存放密码
     *
     *  credentials + slat
     *
     * @param credentials   // 类比 password
     * @param slat  // 通过随机slat 获取
     * @return
     */
    public static String md5Encrypt(String credentials, String slat) {
        return new SimpleHash(algorithmName, credentials, slat, hashIterations).toHex();
    }

    public static boolean isUser() {
        return getSubject() != null && getSubject().getPrincipal() != null;
    }

    public static boolean isGuest() {
        return !isUser();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 是否以认证 (不包含 记住我功能)
     * @return
     */
    public static boolean isAuthenticated() {
        return getSubject() != null && getSubject().isAuthenticated();
    }

//    public static SystemUser getUser() {
//        if(isGuest()) {
//            return null;
//        } else {
//            return
//        }
//    }
}
