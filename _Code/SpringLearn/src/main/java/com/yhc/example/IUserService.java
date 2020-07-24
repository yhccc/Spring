package com.yhc.example;

public interface IUserService {

    void register(User user);
    void login(String username, String password);
}
