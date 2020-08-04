## 三十二、Spring框架中YML的使用

### 1）什么是YML

> YML(YAML)是一种新形式的配置文件，比XML更简单，比Properties更强大。
>
> YAML is a nice human-readable format for configuration, and it has some useful hierarchical properties. It's more or less a superset of JSON, so it has a lot of similar features.

### 2）Properties进行配置存在的问题

> 1. Properties表达过于繁琐，无法表达数据的内在联系
> 2. Properties无法表达对象 集合类型

### 3）YML语法简介

```yaml
1. 定义yml文件
	xxx.yml	xxx.yaml
2. 语法
	2.1 基本语法
		name: yhc
		password: 123456
	2.2 对象概念
		account: 
			id: 1
			name: yhc
			password: 123456
	2.3 定义集合
		service: 
			- 11111
			- 22222
```

### 4）Spring与YML集成思路分析

> 1. 准备yml配置文件
>
> 2. 读取YML并转换成Properties
>
>    - YamlPropertiesFactoryBean.setResources( yml配置文件的路径 )
>    - YamlPropertiesFactoryBean.getObject()  ----->  Properties
>
> 3. 应用PropertySourcesPlaceholderConfigurer
>
>    PropertySourcesPlaceholderConfigurer.setProperties()
>
> 4. 类中应用@Value注解注入

### 5）Spring与YML集成编码

#### 5.1）开发环境

```xml
<dependency>
	<groupId>org.yaml</groupId>
	<artifactId>snakeyaml</artifactId>
	<version>1.25</version>
</dependency>

最低版本 1.18
```

#### 5.2）编码

- 准备yml配置文件

  ```yaml
  mybatis:
    driverClass: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/yhc?useSSL=false
    username: root
    password: 476004
    typeAliasesPackages: com.yhc.mybatis
    mapperLocations: com.yhc.mapper/*Mapper.xml
  ```

- 配置bean中操作

  - 读取YML并转换成Properties
  - 创建PropertySourcesPlaceholderConfigurer

  ```java
  @Bean
  public PropertySourcesPlaceholderConfigurer configurer(){
      // 读取yml并转换成Properties
      YamlPropertiesFactoryBean yamlPropertiesFactoryBean = 
          new YamlPropertiesFactoryBean();
      yamlPropertiesFactoryBean.setResources(new ClassPathResource("mybatis.yml"));
      Properties properties = yamlPropertiesFactoryBean.getObject();
  
      // 创建PropertySourcesPlaceholderConfigurer
      PropertySourcesPlaceholderConfigurer configurer = 
          new PropertySourcesPlaceholderConfigurer();
      configurer.setProperties(properties);
      return configurer;
  }
  ```

- 类中应用@Value注解注入

  ```java
  @Component
  public class MyBatisProperties {
  
      @Value("${mybatis.driverClass}")
      private String driverClass;
      @Value("${mybatis.url}")
      private String url;
      @Value("${mybatis.username}")
      private String username;
      @Value("${mybatis.password}")
      private String password;
      @Value("${mybatis.typeAliasesPackages}")
      private String typeAliasesPackages;
      @Value("${mybatis.mapperLocations}")
      private String mapperLocations;
      
      get set
  }
  ```

### 6）Spring与YML集成的问题

- 集合处理的问题

  > SpringEL表达式进行解决
  >
  > ```java
  > @Value("#{'${list}'.split(',')}")
  > ```

- 对象类型的yml进行配置时过于繁琐

  ```java
  @Value("${mybatis.driverClass}")
  ```

> SpringBoot 使用 @ConfigurationProperties 解决上述两个问题