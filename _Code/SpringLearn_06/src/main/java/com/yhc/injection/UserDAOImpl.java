package com.yhc.injection;

public class UserDAOImpl implements  UserDAO {

    @Override
    public void save() {
        System.out.println("UserDAOImpl.save");
    }
}
