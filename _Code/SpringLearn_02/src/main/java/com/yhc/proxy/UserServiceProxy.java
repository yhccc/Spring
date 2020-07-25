package com.yhc.proxy;

public class UserServiceProxy implements IUserService {

    // 有原始类对象
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void register(User user) {
        System.out.println("---log---");
        userService.register(user);
    }

    @Override
    public void login(String username, String password) {
        System.out.println("---log---");
        userService.login(username, password);
    }
}
