package com.yhc.beanpost;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    // BeanPostProcessor接口带有默认实现
    // default关键字


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }



    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Categroy) {
            Categroy c = (Categroy) bean;
            c.setName("xiaowb");
            System.out.println("postProcessAfterInitialization");
        }
        return bean;
    }
}
