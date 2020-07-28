package com.yhc.cglib;

import com.yhc.proxy.a.User;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestCGlib {
    public static void main(final String[] args) {
        // 1.创建原始对象
        final UserService userService = new UserService();

        // 2.通过cglib方式创建动态代理对象
        // Enhancer.create() -> 代理

        MethodInterceptor interceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("--before--");
                // 原始方法运行
                Object ret = method.invoke(userService, args);
                System.out.println("--after--");
                return ret;
            }
        };

        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(TestCGlib.class.getClassLoader());
        // 设置父类
        enhancer.setSuperclass(userService.getClass());
        // 设置额外功能
        enhancer.setCallback(interceptor);
        UserService userServiceProxy = (UserService) enhancer.create();

        userServiceProxy.login("yhc", "123456");
        userServiceProxy.register(new User());
    }
}
