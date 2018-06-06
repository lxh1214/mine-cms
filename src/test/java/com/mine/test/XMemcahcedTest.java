package com.mine.test;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by liuxianhu on 2018/6/6.
 */
public class XMemcahcedTest extends BasicTest {

    @Test
    public void sigleton() {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(
                AddrUtil.getAddresses("127.0.0.1:11211"));

        builder.setCommandFactory(new BinaryCommandFactory());

        MemcachedClient memcachedClient = null;
        try {
            try {
                memcachedClient = builder.build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                // 设置/获取
                memcachedClient.set("zlex", 36000, "set/get");
                assertEquals("set/get", memcachedClient.get("zlex"));

                // 替换
                memcachedClient.replace("zlex", 36000, "replace");
                assertEquals("replace", memcachedClient.get("zlex"));

                // 移除
                memcachedClient.delete("zlex");
                assertNull(memcachedClient.get("zlex"));
            } catch (TimeoutException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (MemcachedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }finally {
            if (memcachedClient != null) {
                try {
                    memcachedClient.shutdown();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void mTest() {

        MemcachedClient memcachedClient = context.getBean(MemcachedClient.class);
        String str = "helloValue";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", 11);
        try {
            memcachedClient.set("hello", 0, str);
            memcachedClient.set("int", 0, 1);
            memcachedClient.set("map", 0, map);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

        try {
            String strV = memcachedClient.get("hello");
            int intV = memcachedClient.get("int");
            Map<String, Object> mapV = memcachedClient.get("map");
            System.out.println("strV :"+strV);
            System.out.println("intV :"+intV);
            System.out.println("mapV :"+mapV);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

    }
}
