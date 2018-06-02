package com.mine.test;

import com.mine.core.ShiroTool;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by angle on 2018/6/1.
 */
public class ShiroToolTest {

    @Test
    public void randomSlatTest() {

        Set set = new HashSet();
        for(int i=0; i<100; i++) {
            String slat = ShiroTool.getRandomSlat();
            set.add(slat);
            System.out.println(slat);
        }

        Assert.assertEquals(100, set.size());
    }
}
