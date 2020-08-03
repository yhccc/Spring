package com.yhc.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.yhc.aop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

}
