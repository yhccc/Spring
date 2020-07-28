package com.yhc.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext1.xml");
        IUserService userService = (IUserService) ctx.getBean("userService");

        userService.login("yhc", "123456");
        userService.register(new User());
    }
}
