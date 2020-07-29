package com.yhc.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspectProxy {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext2.xml");
        IUserService userService = (IUserService) ctx.getBean("userService");

        userService.login("yhc", "123456");
        userService.register(new User());
    }
}
