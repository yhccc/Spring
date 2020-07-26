package com.yhc.proxy;

public interface IUserService {

    void register(User user);
    void login(String username, String password);
}
