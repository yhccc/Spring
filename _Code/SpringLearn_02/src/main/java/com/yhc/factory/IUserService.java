package com.yhc.factory;

public interface IUserService {

    void register(User user);
    void login(String username, String password);
}
