## 二十四、Spring的事务处理

### 1）什么是事务？

> 保证业务操作完整性的一种数据库机制
>
> 事务的四个特点：`A`原子性 `C`一致性 `I`隔离性 `D`持久性

### 2）如何控制事务？

> - JDBC：
>   - Connection.setAutoCommit(false)  -->  开启事务
>   - Connection.commit()  -->  提交事务
>   - Connection.rollback()  -->  回滚事务
>
> - MyBatis：
>   - MyBatis自动开启事务
>   - sqlSession(封装了Connection).commit()  -->  提交事务
>   - sqlSession(封装了Connection).rollback()  -->  回滚事务
>
> 结论：控制事务的底层，都是Connection对象完成的

### 3）Spring控制事务的开发

> Spring是通过AOP的方式进行事务开发
>
> 1. 原始对象
>
>    ```java
>    public class XXXServiceImpl{
>        1. 原始对象 -> 原始方法 -> 核心功能(业务处理+DAO调用)
>        2. DAO作为Service的成员变量，依赖注入的方式进行赋值
>    }
>    ```
>
> 2. 额外功能
>
>    ```java
>    // Spring提供了一个封装类
>    // org.springframework.jdbc.datasource.DataSourceTransactionManager
>    // 需要为其注入连接Connection(连接池DataSource)
>    
>    // 1.MethodInterceptor
>    public Object invoke(MethodInvocation invocation){
>        try{
>            Connection.setAutoCommit(false);
>            Object ret = invocation.proceed();
>            Connection.commit();
>        } catch(Exception e){
>            Connection.rollback();
>        }
>    }
>    // 2.@Aspect  @Around
>    ```
>
> 3. 切入点
>
>    ```java
>    @Transactional
>    事务的额外功能加入给哪些业务方法
>    1. 加在类上，类中所有的方法都会加入事务
>    2. 加在方法上，这个方法会加入事务
>    ```
>
> 4. 组装切面
>
>    ```xml
>    <!--切入点：自动扫描注解-->
>    <!--额外功能 transaction-manager 指定-->
>    
>    <tx:annotation-driven transaction-manager=""/>
>    ```

### 4）Spring控制事务的编码

#### 4.1）环境配置

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-tx</artifactId>
  <version>5.1.14.RELEASE</version>
</dependency>
```

#### 4.2）编码

1. 原始对象

   ```java
   public class UserServiceImpl implements UserService {
   
       private UserDAO userDAO;
   
       public UserDAO getUserDAO() {
           return userDAO;
       }
   
       public void setUserDAO(UserDAO userDAO) {
           this.userDAO = userDAO;
       }
   
       @Override
       public void register(User user) {
           userDAO.save(user);
       }
   }
   ```

   ```xml
   <!--Service-->
   <bean id="userService" class="com.yhc.service.UserServiceImpl">
       <property name="userDAO" ref="userDAO"/>
   </bean>
   ```

2. 额外功能

   ```xml
   <!--DataSourceTransactionManager-->
   <bean id="dataSourceTransactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
       <property name="dataSource" ref="dataSource"/>
   </bean>
   ```

3. 切入点

   ```java
   @Transactional
   public class UserServiceImpl implements UserService {
   	......
   }
   ```

4. 组装切面

   ```xml
   <tx:annotation-driven transaction-manager="dataSourceTransactionManager"/>
   ```