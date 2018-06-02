package com.mine.core;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

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
}
