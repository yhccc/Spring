package com.yhc.aspect;

public interface IUserService {

    void register(User user);
    void login(String username, String password);
}
