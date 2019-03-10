# spring事务机制

* spring 事务机制： 事务抽象，事务传播、事务隔离
* 代码方式实现事务，标签方式实现事务；
* jpa,jms事务实现。


## spring 事务管理

* 提供统一的api接口支持不同的资源；
* 提供声明式事务管理；
* 方便的与spring框架集成
* 多个资源的事务管理与同步


## spring 事务抽象

提供了三个接口  
* PlatformTransactionManger, 事务的管理,该接口有如下实现。
  * DataSourceTransactionManger
  * JpaTransactionManger，最常用
  * JmsTransactionManger，用消息中间件的时候使用它来管理
  * JtaTransactionManger
* TransactionDefinition，事务的定义
* TransactionStatus，事务的运行状态
