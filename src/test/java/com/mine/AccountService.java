package com.mine;

import org.springframework.cache.annotation.Cacheable;

/**
 * Created by angle on 2018/6/4.
 */
public class AccountService {

    /**
     *  cacheable value 关联 Spring-cache-anno.xml
     *  缓存
     * @param name
     * @return
     */
    @Cacheable(value="accountCache")
    public Account getAccountByName(String name) {
        System.out.println("real query account."+ name);
        return getFromDB(name);
    }

    private Account getFromDB(String name) {
        System.out.println("real querying db..."+name);
        return new Account(name);
    }
}
