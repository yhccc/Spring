package com.yhc.action;

import com.opensymphony.xwork2.Action;
import com.yhc.entity.User;
import com.yhc.service.UserService;

public class RegAction implements Action {

    private UserService userService;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {

        userService.register(user);

        return Action.SUCCESS;
    }
}
