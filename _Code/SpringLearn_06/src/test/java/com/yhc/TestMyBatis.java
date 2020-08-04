package com.yhc;

import com.yhc.mybatis.MyBatisProperties;
import com.yhc.mybatis.User;
import com.yhc.mybatis.UserDAO;
import com.yhc.mybatis.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class TestMyBatis {

    /*
        用于测试Spring+MyBatis注解版整合
     */
    @Test
    public void test1(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.yhc.mybatis.MyBatisAppConfig.class);
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");

        User user = new User();
        user.setName("annotation");
        user.setPassword("123456789");
        userDAO.save(user);
    }

    /*
        用于测试注解版事务开发
    */
    @Test
    public void test2(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.yhc.mybatis");
        UserService userServiceImpl = (UserService)ctx.getBean("userServiceImpl");

        User user = new User();
        user.setName("txAnnotation");
        user.setPassword("555555");
        userServiceImpl.register(user);
    }

    /*
        用于测试yml集合
     */
    @Test
    public void test3(){

        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.yhc.mybatis");
        MyBatisProperties properties = (MyBatisProperties) ctx.getBean("myBatisProperties");

        List<String> list = properties.getList();
        for (String s : list) {
            System.out.println("s = " + s);
        }
    }
}
