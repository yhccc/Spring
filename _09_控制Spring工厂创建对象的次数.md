## 九、控制Spring工厂创建对象的次数

### 1）如何控制简单对象的创建次数

```xml
<!--使用scope属性，默认值为singleton-->
<bean id="account" class="com.yhc.scope.Account" scope="prototype/singleton"/>
```

### 2）如何控制复杂对象的创建次数

```Java
FactoryBean{
    isSingleton(){
        return true; // 只会创建一次
        return false; // 每一次获取对象都会创建新的对象
    }
}
// 若没有实现isSingleton方法（实例工厂，静态工厂），仍然以scope属性进行创建次数的控制
```

### 3）为什么要控制对象的创建次数？

- 好处：节省不必要的内存浪费

- 什么样的对象只创建一次？
  - SqlSessionFactory
  - DAO
  - Service
- 什么样的对象 每一次都要创建新的？
  - Connection
  - SqlSession | Session
  - Controller -> Struts2 Action

> Struts2是一个基于MVC设计模式的Web应用框架，在MVC设计模式中，Struts2作为控制器(Controller)来建立模型与视图的数据交互。