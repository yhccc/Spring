package com.yhc;

import com.yhc.aop.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAnnotation1 {

    @Test
    public void test1(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.yhc.aop.AppConfig.class);
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        userService.register();
    }
}
