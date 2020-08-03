package com.yhc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
@Component
public class MyAspect {

    @Pointcut("execution(* *..UserServiceImpl.*(..))")
    public void myPointCut(){}

    @Around(value = "myPointCut()")
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

    @Around(value = "myPointCut()")
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