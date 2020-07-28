package com.yhc.jdk;

import com.yhc.proxy.a.IUserService;
import com.yhc.proxy.a.User;
import com.yhc.proxy.a.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJDKProxy {


    public static void main(String[] args) {
        // 1. 创建原始对象
        final IUserService userService = new UserServiceImpl();
        // 2. JDK创建动态代理
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("--before--");

                // 原始方法运行
                Object ret = method.invoke(userService, args);

                System.out.println("--after--");

                return ret;
            }
        };

        IUserService userServiceProxy = (IUserService) Proxy.newProxyInstance(
                TestJDKProxy.class.getClassLoader(),
                userService.getClass().getInterfaces(),
                handler);

        userServiceProxy.login("yhc", "123456");
        userServiceProxy.register(new User());
    }

}
