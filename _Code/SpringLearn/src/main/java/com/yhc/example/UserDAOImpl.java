package com.yhc.example;

public class UserDAOImpl implements IUserDAO {

    @Override
    public void save(User user) {
        System.out.println("insert into user=" + user.toString());
    }

    @Override
    public void queryUserByUsernameAndPassword(String username, String password) {
        System.out.println("query User username=" + username + ", password=" + password);
    }
}
