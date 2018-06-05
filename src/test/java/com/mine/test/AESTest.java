package com.mine.test;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.crypto.CryptoException;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by liuxianhu on 2018/6/4.
 */
public class AESTest {

    @Test
    public void generateAESKeyTest() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String key = "lxh";
        byte[] bytesOfMessage = key.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] b = md.digest(bytesOfMessage); //Returns the SHA 256 hash and converts it into byte
// Continue with your code
        Key secretKey = new SecretKeySpec(b , "AES");
        System.out.println(secretKey.toString());

        byte[] bystes =  Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");

        System.out.println(bystes.length);

        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();

        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        Base64.decode("2AvVhdsgUs0FSA3SDFAdag==");

    }

    @Test
    public void Base64Test() {
        String keyStr = "mine-cms";

        byte[] keys;
        try {
            keys = keyStr.getBytes("UTF-8");
            System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys, 64)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testAesCipherService() {
        CipherService cipherService = new AesCipherService();
        // aesCipherService.setKeySize(128);//设置key长度
        // byte[] key = aesCipherService.generateNewKey().getEncoded();
        // String base64 = Base64.encodeToString(key);
        byte[] key = Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");
        System.out.println("key2==" + key.toString());
        System.out.println(Base64.encodeToString(key));
        // 生成key
        // Key key = aesCipherService.generateNewKey();

        String text = "hello";
        System.out.println(new String(text.getBytes()));
        // 加密
        try {
            String encrptText = cipherService.encrypt(text.getBytes(
            ), key).toHex();
            System.out.println("encrptText==" + encrptText);
            // 解密
            String text2 = new String(cipherService.decrypt(
                    Hex.decode(encrptText), key).getBytes());
            System.out.println("text2==" + text2);
            Assert.assertEquals(text, text2);
        } catch (CryptoException e) {
            e.printStackTrace();
        }
    }
}
