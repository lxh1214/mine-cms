import com.github.pagehelper.Page;
import com.mine.core.ShiroTool;
import com.mine.model.SystemUser;
import com.mine.model.SystemUserExample;
import com.mine.service.SystemUserService;
import com.mine.test.BasicTest;
import org.apache.shiro.SecurityUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by liuxianhu on 2018/5/29.
 */
public class SystemUserTest extends BasicTest{

    private final static Logger logger = LoggerFactory.getLogger(SystemUserTest.class);

    SystemUserService service = null;

    @Override
    public void setUp() {
        super.setUp();
        service = context.getBean(SystemUserService.class);
//        SystemUser systemUser = new SystemUser();
//        systemUser.setAdmin(true);
//        systemUser.setCreateDate(new Date());
//        systemUser.setEmail("lxh@lvmama.com");
//        systemUser.setMobilePhoneNumber("13170700404");
//        systemUser.setDeleted(false);
//        systemUser.setUsername("admin");
//        service.saveSystemUser(systemUser);
//        for(int i=10; i<20; i++) {
//            SystemUser systemUser1 = new SystemUser();
//            systemUser1.setAdmin(true);
//            systemUser1.setCreateDate(new Date());
//            systemUser1.setEmail("lxh"+i+"@lvmama.com");
//            systemUser1.setMobilePhoneNumber("1317070040"+i);
//            systemUser1.setDeleted(false);
//            systemUser1.setUsername("admin"+i);
//            service.saveSystemUser(systemUser1);
//        }

//        logger.info("systemUser " + systemUser.getId());
    }

    @Test
    public void generatorEntryptUserTest() {

        SystemUser systemUser = new SystemUser();

        systemUser.setUsername("lxh");
        systemUser.setSalt(ShiroTool.getRandomSlat());
        systemUser.setPassword(ShiroTool.md5Encrypt("1214", systemUser.getSalt()));
        service.saveSystemUser(systemUser);


    }

    @Test
    public void changeUserTest() {
        SystemUser systemUser = service.recieveByUserName("lxh");
        SystemUserExample systemUserExample = new SystemUserExample();
        systemUserExample.createCriteria().andUsernameEqualTo("lxh");
        systemUser.setPassword(ShiroTool.md5Encrypt("lxh", systemUser.getSalt()));
        service.updateSystemUser(systemUser, systemUserExample);
    }

    @Test
    public void findSystemUserTest() {
       // service.getSystemUser(0L);
        SystemUserExample systemUserExample = new SystemUserExample();

//        systemUserExample.createCriteria().andUsernameEqualTo("admin");

        systemUserExample.setOrderByClause("admin desc");

        List<SystemUser> list = service.findSysteUser(systemUserExample);

        logger.info("list size " + list.size());

        //Assert.assertEquals(list.size(), 1);

    }

    @Test
    public void pageSystemUserTest() {
        SystemUserExample systemUserExample = new SystemUserExample();
        Page<SystemUser> page = service.pageBySystemUserExample(1, 10, systemUserExample);
        logger.info("total " + page.getTotal() + "," + "pages:" + page.getPages());
        logger.info("page :"+page);
    }

    @Test
    public void updateSystemUserTest() {
        SystemUserExample systemUserExample = new SystemUserExample();

        systemUserExample.createCriteria().andUsernameEqualTo("admin");

        systemUserExample.setOrderByClause("id desc");

        List<SystemUser> list = service.findSysteUser(systemUserExample);

        SystemUser systemUser = list.get(0);

        systemUser.setUsername("admin1");

        service.updateSystemUser(systemUser, systemUserExample);


    }

    @Override
    public void tearDwon() {

        SystemUserExample systemUserExample = new SystemUserExample();
        systemUserExample.createCriteria().andUsernameEqualTo("admin");
       // logger.info("delete : " +  service.deleteSystemUser(systemUserExample));

        super.tearDwon();
    }
}
