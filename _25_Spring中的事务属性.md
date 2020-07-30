## 二十五、Spring中的事务属性

### 1）什么是事务属性(Transaction Attribute)

> 属性：描述物体特征的一系列值
>
> 事务属性：描述事务特征的一系列值 
>
> 1. 隔离属性 -> isolation
>
>  	2. 传播属性 -> propagation
>  	3. 只读属性 -> readonly
>  	4. 超时属性 -> timeout
>  	5. 异常属性 -> rollbackFor、noRollbackFor

### 2）如何添加事务属性

> @Transactional(isolation=, propagation=, readonly=, timeout=, rollbackFor=, noRollbackFor=)

### 3）事务属性详解

#### 3.1）隔离属性（ISOLATION）

> 概念：描述了事务解决并发问题的特征
>
> 1. 什么是并发？
>
>    多个事务（用户）在同一时间，访问操作了相同的数据
>
>    同一时间：宏观上的同一时间，可能相差几毫秒
>
> 2. 并发会产生哪些问题？
>
>    - 脏读：一个事务，读取了另一个事务没有提交的数据。会在本事务中产生数据不一致的问题
>      - 解决方案：@Transactional(isolation=Isolation.READ_COMMITTED)
>    - 不可重复读：一个事务中多次读取相同的数据，但是读取结果不一样。会在本事务中产生数据不一致的问题
>      - 注意：不是脏读；一个事务中
>      - 解决方案：@Transactional(isolation=Isolation.REPEATABLE_READ)
>      - 本质：行锁
>    - 幻影读：一个事务中，多次对整表进行查询统计，但是结果不一样。会在本事务中产生数据不一致的问题
>      - 解决方案：@Transactional(isolation=Isolation.SERIALIZABLE)
>      - 本质：表锁
>
> 3. 并发问题如何解决？
>
>    通过隔离属性解决，隔离属性中设置不同的值，解决并发处理过程中的问题
>
> 总结：
>
> ​		并发安全：SERIALIZABLE > REPEATABLE_READ > READ_COMMITTED
>
> ​		运行效率：READ_COMMITTED > REPEATABLE_READ  > SERIALIZABLE 

- 数据库对于隔离属性的支持

  |  隔离属性的值   | MySQL | Oracle |
  | :-------------: | :---: | :----: |
  | READ_COMMITTED  |   √   |   √    |
  | REPEATABLE_READ |   √   |   ×    |
  |  SERIALIZABLE   |   √   |   √    |

  Oracle不支持REPEATABLE_READ值，如何解决不可重复读？

  采用的是多版本比对的方式，解决不可重复读的问题。

- 默认隔离属性
  - ISOLATION-DEFAULT：会调用不同数据库所设置的默认隔离属性
  - MySQL：REPEATABLE_READ
  - Oracle：READ_COMMITTED

- 查看数据库默认隔离属性

  - MySQL

    - select @@tx_isolation;

  - Oracle

    ```sql
    --首先创建一个事务
    declare
         trans_id Varchar2(100);
      begin
         trans_id := dbms_transaction.local_transaction_id( TRUE );
      end; 
    --查看事务隔离级别
    SELECT s.sid, s.serial#,
    　　CASE BITAND(t.flag, POWER(2, 28))
    　　　　WHEN 0 THEN 'READ COMMITTED'
    　　　　ELSE 'SERIALIZABLE'
    　　END AS isolation_level
    FROM v$transaction t
    JOIN v$session s ON t.addr = s.taddr AND s.sid = sys_context('USERENV', 'SID');
    ```

- 隔离属性在实战中的建议

  > 1. 推荐使用Spring指定的ISOLATION_DEFAULT
  >
  > 2. 未来中的实战中，并发访问情况  很低（海量）
  >
  > 3. 如果真遇到并发问题，通过乐观锁解决
  >
  >    Hibernate(JPA)  Version
  >
  >    MyBatis              通过拦截器自定义开发

#### 3.2）传播属性（PROPAGATION）

> 概念：描述了事务解决嵌套问题的特征
>
> 事务嵌套：一个大事务中包含了若干个小事务
>
> 问题：大事务中融入了很多小的事务，它们彼此影响，最终就会导致外部大的事务，丧失了事务的原子性

- 传播属性的值及其用法

| 传播属性的值  | 外部不存在事务 |        外部存在事务        |                         用法                          |    备注    |
| :-----------: | :------------: | :------------------------: | :---------------------------------------------------: | :--------: |
|   REQUIRED    |  开启新的事务  |      融合到外部事务中      |   @Transactional(propagation=Propagation.REQUIRED)    | 增删改方法 |
|   SUPPORTS    |   不开启事务   |      融合到外部事务中      |   @Transactional(propagation=Propagation.SUPPORTS)    |  查询方法  |
| REQUIRES_NEW  |  开启新的事务  | 挂起外部事物，创建新的事务 | @Transactional(propagation=Propagation.REQUIRES_NEW)  |  日志记录  |
| NOT_SUPPORTED |   不开启事务   |        挂起外部事物        | @Transactional(propagation=Propagation.NOT_SUPPORTED) | 极其不常用 |
|     NEVER     |   不开启事务   |          抛出异常          |     @Transactional(propagation=Propagation.NEVER)     | 极其不常用 |
|   MANDATORY   |    抛出异常    |       融合到外部事务       |   @Transactional(propagation=Propagation.MANDATORY)   | 极其不常用 |

- 默认的传播属性
  - REQUIRED是传播属性的默认值

- 推荐传播属性的使用方式
  - 增删改 方法：直接使用默认值REQUIRED
  - 查询     方法：显示指定传播属性为SUPPORTS

### 3.3）只读属性（READONLY）

> 针对于只进行查询操作的业务方法，可以加入只读属性，提高运行效率
>
> 默认值：false

### 3.4）超时属性（TIMEOUT）

> 指定了事务等待的最长时间
>
> 1. 为什么事务会进行等待？
>
>    当前事务访问数据时，有可能访问的数据被别的事务进行加锁的处理，那么此时本事务就必须进行等待
>
> 2. 等待时间单位为秒
>
> 3. 如何应用  @Transactional(timeout =  2)
>
> 4. 超时属性的默认值为 -1
>
>    最终由对应的数据库来指定

### 3.5）异常属性

> Spring事务处理过程中
>
> 默认 对于RuntimeException及其子类 采用的是回滚的策略
>
> 默认 对于Exception及其子类 采用的是提交的策略
>
> rollbackFor = {java.lang.Exception.class, xxx, xxx}
>
> noRollbackFor = {java.lang.RuntimeException.class, xxx, xxx}
>
> 建议：实战中使用RuntimeException及其子类，使用事务异常属性的默认值

### 4）事务属性常见配置总结

> 1. 隔离属性	默认值
> 2. 传播属性    Required(默认值) -> 增删改    Suppor -> 查询操作
> 3. 只读属性    false(默认值) -> 增删改    true -> 查询操作
> 4. 超时属性    默认值 -1
> 5. 异常属性    默认值
>
> 增删改操作 -> @Transactional
>
> 查询操作 -> @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)

### 5）基于标签的事务配置方式（事务开发的第二种形式）

```xml
<!--配置事务属性-->
<tx:advice id='txAdvice' transaction-manager="dataSourceTransactionManager">
	<tx:attributes>
    	<tx:method name="register" isolation="", propagation=""/>
        <!--等效于@Transactional配置-->
    </tx:attributes>
</tx:advice>

<!--aop组装切面-->
<aop:config>
	<aop:pointcut id="pc" expression="execution(* com.yhc.service.UserServiceImpl.register(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="pc"/>
</aop:config>
```

- 基于标签的事务配置在实战种的应用方式

```xml
<!--配置事务属性-->
<!--编程的时候 service中负责进行增删改操作的方法 都以modify开头-->
<tx:advice id='txAdvice' transaction-manager="dataSourceTransactionManager">
	<tx:attributes>
    	<tx:method name="modify*" isolation="", propagation=""/>
        <!--除modify*的其他所有方法-->
        <tx:method name="*" propagation="SUPPORTS" read-only="true"/>
        <!--等效于@Transactional配置-->
    </tx:attributes>
</tx:advice>

<!--aop组装切面-->
<!--编程的时候 Service均放置在service包中-->
<aop:config>
	<aop:pointcut id="pc" expression="execution(* com.yhc.service..*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="pc"/>
</aop:config>
```