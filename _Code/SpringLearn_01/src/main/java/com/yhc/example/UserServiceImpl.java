package com.yhc.example;

public class UserServiceImpl implements IUserService {

    private IUserDAO userDAO;

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public void register(User user) {
        userDAO.save(user);
    }

    @Override
    public void login(String username, String password) {
        userDAO.queryUserByUsernameAndPassword(username, password);
    }
}
