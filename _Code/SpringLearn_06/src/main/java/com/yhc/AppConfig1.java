package com.yhc;

import com.yhc.injection.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("category.properties")
public class AppConfig1 {

    @Value("${id}")
    private Integer id;
    @Value("${name}")
    private String name;

    @Bean
    public UserDAO userDAO(){
        return new UserDAOImpl();
    }

//    @Bean
//    public UserService userService(UserDAO userDAO){
//        UserServiceImpl userService = new UserServiceImpl();
//        userService.setUserDAO(userDAO);
//        return userService;
//    }

    @Bean
    public UserService userService(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDAO(userDAO());
        return userService;
    }

    @Bean
    public Category category(){
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }
}
