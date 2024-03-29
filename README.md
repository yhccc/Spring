# Spring
SpringLearn: B站地址(https://www.bilibili.com/video/BV185411477k)

![SpringLearn](./_Images/SpringFramework5.x.png)

## 章节目录
### Spring IOC Factory
[01.引言](./_01_引言.md)  

[02.第一个Spring程序](./_02_第一个Spring程序.md)  

[03.Spring5.x与日志框架的整合](./_03_Spring5.x与日志框架的整合.md)  

[04.注入(Injection)](./_04_注入(Injection).md)  

[05.Set注入详解](./_05_Set注入详解.md)  

[06.构造注入](./_06_构造注入.md)  

[07.控制反转与依赖注入](./_07_控制反转与依赖注入.md)  

[08.Spring创建复杂对象](./_08_Spring创建复杂对象.md)  

[09.控制Spring工厂创建对象的次数](./_09_控制Spring工厂创建对象的次数.md)  

[10.对象的生命周期](./_10_对象的生命周期.md)  

[11.配置文件参数化](./_11_配置文件参数化.md)  

[12.自定义类型转换器](./_12_自定义类型转换器.md)  

[13.后置处理Bean](./_13_后置处理Bean.md)  

### Spring AOP
[14.静态代理设计模式](./_14_静态代理设计模式.md)  

[15.Spring的动态代理开发](./_15_Spring的动态代理开发.md)  

[16.Spring动态代理详解](./_16_Spring动态代理详解.md)  

[17.AOP编程](./_17_AOP编程.md)  

[18.AOP的底层实现原理](./_18_AOP的底层实现原理.md)  

[19.基于注解的AOP编程](./_19_基于注解的AOP编程.md)  

[20.AOP开发中的一个坑](./_20_AOP开发中的一个坑.md)  

[21.AOP阶段知识总结](./_21_AOP阶段知识总结.md)  

### Spring + Persistence Layer
[22.持久层整合](./_22_持久层整合.md)  

[23.Spring与MyBatis整合](./_23_Spring与MyBatis整合.md)  

[24.Spring的事务处理](./_24_Spring的事务处理.md)  

[25.Spring中的事务属性](./_25_Spring中的事务属性.md)  

### Spring + Struts2 + MyBatis
[26.MVC框架整合思想](./_26_MVC框架整合思想.md)  

[27.Spring与Struts2框架整合](./_27_Spring与Struts2框架整合.md)  

[28.Spring+Struts2+MyBatis整合](./_28_Spring+Struts2+MyBatis整合.md)  

### Annotation Programming
[29.注解基础概念](_29_注解基础概念.md)  

[30.Spring的基础注解](_30_Spring的基础注解.md)  

[31.Spring的高级注解](_31_Spring的高级注解.md)  

[32.Spring框架中YML的使用](_32_Spring框架中YML的使用.md)  

#### 报错解决

> 无法读取方案文档 'https://www.springframework.org/schema/tool/spring-tool.xsd', 原因为 1) 无法找到文档; 2) 无法读取文档; 3) 文档的根元素不是 <xsd:schema>
>
> 问题原因：https://www.springframework.org/schema/tool/spring-tool.xsd  https无法读取  ->  只能读取http的文件

> SSM整合之后，tomcat运行，log4j或logback不再打印日志信息
>
> pom.xml依赖文件中 test 改为 compile 即可
>
> ```xml
> <dependency>
>     <groupId>org.slf4j</groupId>
>     <artifactId>slf4j-log4j12</artifactId>
>     <version>1.7.25</version>
>     <scope>test</scope> -> <scope>compile</scope>
> </dependency>
> ```

<p align="right">更新时间: 2020-08-04</p>