package com.yhc;

import com.yhc.proxy.*;
import com.yhc.proxy.a.IUserService;
import com.yhc.proxy.a.User;
import com.yhc.proxy.a.UserServiceProxy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    // 用于测试静态代理的方法
    @Test
    public void test1(){
        IUserService userService = new UserServiceProxy();
        userService.login("yhc", "123456");
        userService.register(new User());

        IOrderService orderService = new OrderServiceProxy();
        orderService.showOrder();
    }

    // 用于测试Spring的动态代理
    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        IUserService userService = (IUserService) ctx.getBean("userService");

        userService.login("yhc", "123456");
        userService.register(new User());

        IOrderService orderService = (IOrderService) ctx.getBean("orderService");
        orderService.showOrder();
    }
}
