## 三、Spring5.x与日志框架的整合

> Spring与日志框架进行整合，日志框架就可以在控制台中，输出Spring框架运行过程中的一些重要的信息。
>
> - 好处：便于了解Spring框架的运行过程，利于程序的调试
>
> Spring如何整合日志框架
>
> - Spring1、2、3早期都是与commons-logging.jar
> - Spring5.x默认整合的日志框架logback、log4j2
>
> Spring5.x整合log4j
>
> 1. 引入log4j jar包
>
>    ```xml
>    <!-- https://mvnrepository.com/artifact/log4j/log4j -->
>    <dependency>
>        <groupId>log4j</groupId>
>        <artifactId>log4j</artifactId>
>        <version>1.2.17</version>
>    </dependency>
>    
>    <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
>    <dependency>
>        <groupId>org.slf4j</groupId>
>        <artifactId>slf4j-log4j12</artifactId>
>        <version>1.7.25</version>
>        <scope>test</scope>
>    </dependency>
>    ```
>
> 2. 引入log4j.priperties配置文件
>
>    ```properties
>    # resources文件夹根目录下
>    ### 配置根
>    log4j.rootLogger = debug, console
>    
>    ### 日志输出到控制台显示
>    log4j.appender.console = org.apache.log4j.ConsoleAppender
>    log4j.appender.console.Target = System.out
>    log4j.appender.console.layout = org.apache.log4j.PatternLayout
>    log4j.appender.console.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
>    ```

