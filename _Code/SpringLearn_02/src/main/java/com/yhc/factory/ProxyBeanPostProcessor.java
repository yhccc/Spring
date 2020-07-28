package com.yhc.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("--before--");
                Object ret = method.invoke(bean, args);
                System.out.println("--after--");
                return ret;
            }
        };

        return Proxy.newProxyInstance(
                ProxyBeanPostProcessor.class.getClassLoader(),
                bean.getClass().getInterfaces(),
                handler
        );

    }
}
