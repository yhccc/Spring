package com.yhc.service;

import com.yhc.entity.User;

public interface UserService {
    public void register (User user);

    public void login(String name, String password);
}
