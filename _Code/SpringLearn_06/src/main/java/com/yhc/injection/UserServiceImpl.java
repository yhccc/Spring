package com.yhc.injection;


public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        System.out.println("UserServiceImpl.setUserDAO");
        this.userDAO = userDAO;
    }

    @Override
    public void register() {
        userDAO.save();
    }
}
