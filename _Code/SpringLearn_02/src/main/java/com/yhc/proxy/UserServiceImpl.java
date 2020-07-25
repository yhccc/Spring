package com.yhc.proxy;

public class UserServiceImpl implements IUserService {
    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl.register");
    }

    @Override
    public void login(String username, String password) {
        System.out.println("UserServiceImpl.login");
    }
}
