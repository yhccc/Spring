package com.yhc.dynamic;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Around implements MethodInterceptor {

    /*
        invoke方法的作用: 额外功能书写在invoke中
            可以 在原始方法之前
                 在原始方法之前
                 在原始方法之前、之后 执行额外功能
         params:
            MethodInvocation(Method): 额外功能所增加给的原始方法
         return:
            Object: 原始方法的返回值
     */

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("--before--");

        // 原始方法运行
        Object ret = null;
        try {
            ret = methodInvocation.proceed();
        } catch (Throwable throwable)
        {
            System.out.println("--exception--");
            throwable.printStackTrace();
        }

        System.out.println("--after--");

        return ret;
    }
}
