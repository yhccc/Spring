package com.yhc.aop;


import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void register() {
        System.out.println("UserServiceImpl.register");
    }
}
