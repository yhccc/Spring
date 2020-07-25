package com.yhc;

import com.yhc.proxy.IUserService;
import com.yhc.proxy.User;
import com.yhc.proxy.UserServiceProxy;
import org.junit.Test;

public class TestSpring {

    // 用于测试静态代理的方法
    @Test
    public void test1(){
        IUserService userService = new UserServiceProxy();
        userService.login("yhc", "123456");
        userService.register(new User());
    }
}
