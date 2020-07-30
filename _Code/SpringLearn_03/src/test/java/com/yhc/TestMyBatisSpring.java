package com.yhc;

import com.yhc.entity.User;
import com.yhc.dao.UserDAO;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMyBatisSpring {

    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");

        User user = new User();
        user.setName("xiaojr");
        user.setPassword("999999");

        userDAO.save(user);
    }
}
