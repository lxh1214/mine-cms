package com.mine.test;

import com.mine.service.SystemUserService;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liuxianhu on 2018/5/29.
 */
public abstract class BasicTest{

    protected ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @After
    public void tearDwon() {
        context = null;
    }
}
