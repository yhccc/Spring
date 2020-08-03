package com.yhc.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    @Qualifier("userDAOImpl")
//    @Resource(name = "userDAOImpl")
//    @Resource
    @Inject
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
