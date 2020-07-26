package com.yhc.dynamic;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class Before1 implements MethodBeforeAdvice {

    /*
        Method: 额外功能所增加给的那个原始方法
                login方法
        Object[]: 额外功能所增加给的那个原始方法的参数
                login方法接收的参数 String username, String password
        Object: 额外功能所增加给的那个原始对象
                UserServiceImpl
     */

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before1.before");
    }
}
