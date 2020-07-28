package com.yhc.cglib;

import com.yhc.proxy.a.User;

public class UserService {
    public void register(User user) {
        System.out.println("UserService.register");
    }

    public void login(String username, String password) {
        System.out.println("UserService.login");
    }
}
