package com.yhc.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {

    private static Properties env = new Properties();

    static {
        try {
            // 1. 获得IO的输入流
            InputStream inputStream = BeanFactory.class.getResourceAsStream("/applicationContext.properties");
            // 2. 文件内容封装到Properties集合中
            env.load(inputStream);

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        对象的创建方式：
            1. 直接调用构造方法，创建对象 IUserService userService = new UserServiceImpl();
            2. 通过反射的形式 创建对象 解耦合
                Class clazz = Class.forName("");
                IUserService userService = (IUserService) clazz.newInstance();
     */
    public static IUserService getUserService(){

//        return new UserServiceImpl();
        IUserService userService = null;
        try {
            Class clazz = Class.forName(env.getProperty("userService"));
            userService = (IUserService) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return userService;
    }

    public static IUserDAO getUserDAO(){
        IUserDAO userDAO = null;
        try {
            Class clazz = Class.forName(env.getProperty("userDAO"));
            userDAO = (IUserDAO) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return userDAO;
    }

    /*
        key 配置文件中的 key {userDAO, userService}
     */
    public static Object getBean(String key){
        Object ret = null;
        try {
            Class clazz = Class.forName(env.getProperty(key));
            ret = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
