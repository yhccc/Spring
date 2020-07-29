## 二十、AOP开发中的一个坑

> 在同一个业务类中，进行业务方法间的相互调用，只有最外层方法才是加入了额外功能的（内部的方法，通过普通方式调用，都调用的是原始方法）。
>
> 如果想让内层的方法也调用代理对象的方法，就要实现`ApplicationContextAware`接口的`setApplicationContext`方法获得Spring工厂，进而获得代理对象。

```java
public class UserServiceImpl implements IUserService, ApplicationContextAware {

    private ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = ctx;
    }

    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl.register");

        // 调用的是原始对象的login方法 -> 只能完成核心功能
        // 设计目的：执行代理对象的login方法 -> 核心功能 + 额外功能
        IUserService userService = (IUserService)ctx.getBean("userService");
        userService.login("yhc", "123456");
    }

    @Override
    public void login(String username, String password) {
        System.out.println("UserServiceImpl.login");
    }
}
```

