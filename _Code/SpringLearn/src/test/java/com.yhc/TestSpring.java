package com.yhc;


import com.yhc.example.*;
import com.yhc.example.constructor.Customer;
import com.yhc.factorybean.ConnectionFactoryBean;
import com.yhc.life.Product;
import com.yhc.scope.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

public class TestSpring {

    @Test
    public void test1(){
        IUserService userService = (IUserService) BeanFactory.getBean("userService");
        User user = new User("test", "123456");
        userService.register(user);
        userService.login("zhangsan", "lisi");
    }

    @Test
    public void test2()
    {
        // 获得Spring工厂
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        // 通过工厂类 获得对象
        Person person = (Person) ctx.getBean("person");

        System.out.println("person = " + person);
    }

    @Test
    public void test3()
    {
        // 获得Spring工厂
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        // 通过工厂类 获得对象
//        Person person = ctx.getBean("person", Person.class);
//        System.out.println("person = " + person);

        // 配置文件中只能有一个bean标签class属性为Person类型
        // 否则抛出NoUniqueBeanDefinitionException异常
//        Person person = ctx.getBean(Person.class);

        // 获取Spring工厂配置文件中所有bean标签的id值
//        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
//        for (String name : beanDefinitionNames)
//            System.out.println("beanDefinitionNames=" + name);

        // 根据类型获取Spring工厂配置文件对应的id值
//        String[] beanNamesForType = ctx.getBeanNamesForType(Person.class);
//        for (String name : beanNamesForType)
//            System.out.println("beanDefinitionNames=" + name);

        // 用于判断是否存在指定id值的bean
//        if (ctx.containsBeanDefinition("user1")) {
//            System.out.println("true");
//        }else{
//            System.out.println("false");
//        }

        // 用于判断是否存在指定id值的bean
        if (ctx.containsBean("person")) {
            System.out.println("true");
        }else{
            System.out.println("false");
        }

    }

    @Test
    public void test4(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");

        User user = ctx.getBean(User.class);
        String[] beanNamesForType = ctx.getBeanNamesForType(User.class);
        for (String name : beanNamesForType)
            System.out.println("beanDefinitionNames=" + name);
    }

    @Test
    public void test5(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = ctx.getBean("person", Person.class);
        System.out.println(person);
    }

    /*
        用于测试Set注入
     */
    @Test
    public void test6(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml") ;
        Person person = (Person)ctx.getBean("person");

//        person.setId(1);
//        person.setName("yhc");

        System.out.println("person = " + person);
    }

    @Test
    public void test7(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        IUserService userService = (IUserService)ctx.getBean("userService");
        userService.login("xiaohe", "999999999");
        userService.register(new User("yhc", "456789123"));
    }

    // 测试构造注入
    @Test
    public void test8(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Customer customer = ctx.getBean("customer", Customer.class);
        System.out.println(customer);
    }

    // 测试创建复杂对象
    // FactoryBean
    // 实例工厂
    // 静态工厂
    @Test
    public void test9(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Connection conn = ctx.getBean("conn", Connection.class);
        Connection conn2 = ctx.getBean("conn", Connection.class);

        System.out.println("Connectoion = " + conn);
        System.out.println("Connectoion2 = " + conn2);
//        ConnectionFactoryBean connfb = ctx.getBean("&conn", ConnectionFactoryBean.class);
//        System.out.println("ConnectionFactoryBean = " + connfb);
    }

    // 用于测试简单对象的创建次数
    @Test
    public void test10(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Account account1 = ctx.getBean("account", Account.class);
        Account account2 = ctx.getBean("account", Account.class);
        System.out.println(account1);
        System.out.println(account2);
    }

    @Test
    public void test11(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Product product =  ctx.getBean("product", Product.class);
        ((ClassPathXmlApplicationContext) ctx).close();
    }

    @Test
    public void test12(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext1.xml");
        Connection connection = (Connection) ctx.getBean("conn");
        System.out.println("conn = " + connection);
    }

}