package com.yhc;

import com.yhc.entity.User;
import com.yhc.dao.UserDAO;
import com.yhc.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMyBatisSpring {

    /*
        用于测试：Spring与MyBatis的整合
     */
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");

        User user = new User();
        user.setName("xiaojr");
        user.setPassword("999999");

        userDAO.save(user);
    }

    /*
        用于测试：Spring的事务处理
    */
    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userService");

        User user = new User();
        user.setName("xwb");
        user.setPassword("555666");
        userService.register(user);
    }

    /*
        用于测试：Spring的基于标签事务属性
     */
    @Test
    public void test3(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_tx.xml");
        UserService userService = (UserService) ctx.getBean("userService");

        User user = new User();
        user.setName("xwb2");
        user.setPassword("778899");
        userService.register(user);
    }
}
