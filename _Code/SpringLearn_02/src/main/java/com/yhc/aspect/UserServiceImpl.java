package com.yhc.aspect;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UserServiceImpl implements IUserService, ApplicationContextAware {

    private ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl.register");

        // 调用的是原始对象的login方法 -> 只能完成核心功能
        // 设计目的：执行代理对象的login方法 -> 核心功能 + 额外功能
        IUserService userService = (IUserService)ctx.getBean("userService");
        userService.login("yhc", "123456");
    }

    @Override
    public void login(String username, String password) {
        System.out.println("UserServiceImpl.login");
    }
}
