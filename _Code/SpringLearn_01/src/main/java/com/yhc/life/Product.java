package com.yhc.life;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Product implements InitializingBean, DisposableBean {
//public class Product{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Product.setName");
        this.name = name;
    }

    public Product(){
        System.out.println("Product.Product");
    }

    // 初始化方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Product.afterPropertiesSet");
    }

    public void myInit(){
        System.out.println("Product.myInit");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Product.destroy");
    }

    public void myDestory(){
        System.out.println("Product.myDestory");
    }
}
