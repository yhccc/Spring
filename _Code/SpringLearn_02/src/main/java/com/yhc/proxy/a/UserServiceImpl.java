package com.yhc.proxy.a;

import com.yhc.Log;

public class UserServiceImpl implements IUserService {
    @Log
    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl.register");
//        throw new RuntimeException("测试异常");
    }

    @Override
    public void login(String username, String password) {
        System.out.println("UserServiceImpl.login");
    }
}
