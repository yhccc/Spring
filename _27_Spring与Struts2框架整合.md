## 二十七、Spring与Struts2框架整合（选学）

### 1）Spring与Struts2整合思路分析

> Struts2中的Action需要通过Spring的依赖注入获得Service对象

### 2）Spring与Struts2整合的编码实现

#### 2.1）搭建开发环境

1. 引入相关jar包（Spring、Struts2）

   ```xml
   <!-- https://mvnrepository.com/artifact/org.apache.struts/struts2-spring-plugin -->
   <dependency>
       <groupId>org.apache.struts</groupId>
       <artifactId>struts2-spring-plugin</artifactId>
       <version>2.3.8</version>
   </dependency>
   ```

2. 引入对应的配置文件

   - Spring相关 -> applicationContext.xml
   - struts2相关 -> struts.xml
   - 日志相关 -> log4j.properties

3. 初始化配置

   - Spring相关 -> 工厂创建（ContextLoaderListener -> web.xml）
   - Struts2（Filter -> web.xml）

   ```xml
   <listener>
       <listener-class>
           org.springframework.web.context.ContextLoaderListener
       </listener-class>
   </listener>
   
   <context-param>
       <param-name>contextConfigLocation</param-name>
       <param-value>classpath:applicationContext.xml</param-value>
   </context-param>
   
   <filter>
       <filter-name>struts2</filter-name>
       <filter-class>
           org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter
       </filter-class>
   </filter>
   <filter-mapping>
       <filter-name>struts2</filter-name>
       <url-pattern>/*</url-pattern>
   </filter-mapping>
   ```

### 2.2）编码

1. 开发Service对象
   - Java类
   - applicationContext创建bean对象

2. 开发Action对象

   - Java类

   ```java
   import com.opensymphony.xwork2.Action;
   
   public class RegAction implements Action {
   
       private UserService userService;
   
       public UserService getUserService() {
           return userService;
       }
   
       public void setUserService(UserService userService) {
           this.userService = userService;
       }
   
       @Override
       public String execute() throws Exception {
           userService.register();
           return Action.SUCCESS;
       }
   }
   ```

   - Spring配置

   ```xml
   <bean id="regAction" scope="prototype" class="com.yhc.struts2.RegAction">
       <property name="userService" ref="userService"/>
   </bean>
   ```

   - struts2配置

   ```xml
   <package name="ssm" extends="struts-default">
       
       <!--url reg.action -> 会接收到用户的请求后，创建RegAction这个类的对象，进行相应的处理-->
       <!--<action name="reg" class="com.yhc.struts2.RegAction">-->
       <action name="reg" class="regAction">
           <result name="success">/index.jsp</result>
       </action>
   </package>
   ```