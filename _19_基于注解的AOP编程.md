## 十九、基于注解的AOP编程

### 1）基于注解的AOP编程的开发步骤

1. 原始对象
2. 额外功能
3. 切入点
4. 组装切面

> 通过 `@Aspect` 切面类 定义了 额外功能 `@Around`
>
> 定义了 切入点     `@Around("execution(* login(..))")`

```java
/*
    切面类 = 额外功能 + 切点
    1. 额外功能
        public class MyAround implements MethodInterceptor{
            public Object invoke(MethodInvocation invocation){

                Object ret = invocation.proceed();

                return ret;
            }
        }
    2. 切入点
        <aop:config>
            <aop:pointcut id="pc" expression="execution(* *(..))" />
        <aop:config/>

 */
@Aspect
public class MyAspect {

    @Around("execution(* login(..))")
    public Object arround(ProceedingJoinPoint joinPoint){

        System.out.println("--before--");
        Object ret = null;
        try {
            ret = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("--after--");

        return  ret;
    }
}
```

```xml
<!--原始对象-->
<bean id="userService" class="com.yhc.aspect.UserServiceImpl"/>

<!--切面类 = 额外功能 + 切入点-->
<bean id="around" class="com.yhc.aspect.MyAspect"/>

<!--告知Spring基于注解进行AOP编程-->
<aop:aspectj-autoproxy/>
```

### 2）注意细节

#### 2.1）切入点复用

```java
@Aspect
public class MyAspect {
	// 在切面类中定义一个函数，增加@Pointcut注解，通过这种方式定义切入点表达式，后续更加有利于切入点的复用以及修改维护
    @Pointcut("execution(* login(..))")
    public void myPointCut(){}

    @Around(value="myPointCut()")
    public Object arround(ProceedingJoinPoint joinPoint){

        System.out.println("--before--");
        Object ret = null;
        try {
            ret = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return  ret;
    }

    @Around(value="myPointCut()")
    public Object arround1(ProceedingJoinPoint joinPoint){

        Object ret = null;
        try {
            ret = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("--after--");

        return  ret;
    }
}
```

#### 2.2）动态代理的创建方式

> AOP底层实现两种代理创建方式
>
> - JDK 通过实现接口，做新的实现类的方式创建代理对象
> - CGlib 通过继承父类，做新的子类创建代理对象
>
> 默认情况，AOP编程底层应用JDK动态代理创建方式
>
> 1. 如何切换成CGlib实现动态代理类的创建？
>
> 	```xml
> 	<aop:aspectj-autoproxy proxy-target-class="true"/>
> 	```
>
> 2. 传统的AOP开发如何实现切换成CGlib实现？
>
>    ```xml
>    <aop:config proxy-target-class="true">
>    ```
>

