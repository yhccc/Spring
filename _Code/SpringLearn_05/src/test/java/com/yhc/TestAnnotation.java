package com.yhc;

import com.yhc.component.User;
import com.yhc.injection.Category;
import com.yhc.injection.UserService;
import com.yhc.lazy.Account;
import com.yhc.life.Product;
import com.yhc.scope.Customer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotation {

    /*
        用于测试 @Component注解
     */
    @Test
    public void test1() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User)ctx.getBean("u");
        System.out.println("user id = " + user.getId());
    }

    /*
        用于测试 @Scope注解
    */
    @Test
    public void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Customer customer01 = (Customer)ctx.getBean("customer");
        Customer customer02 = (Customer)ctx.getBean("customer");
        System.out.println(customer01);
        System.out.println(customer02);
    }

    /*
        用于测试 @Scope注解
    */
    @Test
    public void test3() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Account account = (Account) ctx.getBean("account");
    }

    /*
        用于测试 @Scope注解
    */
    @Test
    public void test4() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ((ClassPathXmlApplicationContext) ctx).close();
    }


    /*
        用于测试 @AutoWired注解
    */
    @Test
    public void test5() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        userService.register();
    }

    /*
        用于测试 @Value注解
    */
    @Test
    public void test6() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Category category = (Category) ctx.getBean("category");
        System.out.println(category);
    }

    /*
        用于测试排除策略
    */
    @Test
    public void test7(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames)
            System.out.println("beanDefinitionName=" + beanDefinitionName);
    }
}
