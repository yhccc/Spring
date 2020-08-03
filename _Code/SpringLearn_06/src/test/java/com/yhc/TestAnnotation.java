package com.yhc;

import com.yhc.bean.User;
import com.yhc.four.Account;
import com.yhc.injection.Category;
import com.yhc.injection.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;

public class TestAnnotation {

    /*
        用于测试配置Bean
     */
    @Test
    public void test1(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.yhc");
        User user = (User)ctx.getBean("user");
        Connection connection = (Connection)ctx.getBean("conn");
//        Connection connection1 = (Connection)ctx.getBean("connection");
//        Connection connection2 = (Connection)ctx.getBean("connection");
        System.out.println(user);
        System.out.println(connection);
//        System.out.println(connection1);
//        System.out.println(connection2);
    }

    /*
        用于测试注入
     */
    @Test
    public void test2() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig1.class);
        UserService userService = (UserService) ctx.getBean("userService");
        Category category = (Category) ctx.getBean("category");
        userService.register();
        System.out.println(category);
    }

    /*
        用于测试注解扫描
     */
    @Test
    public void test3() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig2.class);
    }

    /*
        用于测试优先级
     */
    @Test
    public void test4(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig3.class);
        Category category = (Category) ctx.getBean("category");
        System.out.println(category);
    }

    /*
        用于测试配置bean底层
     */
    @Test
    public void test5(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = (User) ctx.getBean("user");
    }

    /*
        用于测试
     */
    @Test
    public void test6(){
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.yhc.four.AppConfig.class);
        Account account = (Account)ctx.getBean("account");
        System.out.println(account);
    }

}
