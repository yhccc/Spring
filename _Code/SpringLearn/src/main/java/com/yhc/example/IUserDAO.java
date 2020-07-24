package com.yhc.example;

public interface IUserDAO {

    void save(User user);
    void queryUserByUsernameAndPassword(String username, String password);
}
