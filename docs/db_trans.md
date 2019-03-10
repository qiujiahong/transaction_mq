# 数据库事务


## 测试数据表

```sql
DROP TABLE IF EXISTS `t_user`; 
CREATE TABLE t_user (
	id INT NOT NULL AUTO_INCREMENT,
	username CHAR ( 10 ),
	amount INT,
	PRIMARY KEY ( `id` ) 
);
INSERT INTO t_user ( username, amount )
VALUES
	( 'nick', 100 ),
	( 'elaine', 100 );
	
```

## 测试

```sql
BEGIN;
UPDATE t_user SET amount = amount-100 where username ='nick';
UPDATE t_user SET amount = amount+100 where username ='elaine';
rollback;
COMMIT;

SELECT * FROM t_user;
```

## 补充说明

* 查看隔离级别``SELECT @@GLOBAL.tx_isolation, @@tx_isolation;``。  
* 隔离级别： ``SET SESSION TRANSACTION IOSLATION LEVEL READ UNCOMMITTED;`` 
* mysql 支持4个隔离级别： READ UNCOMMITTED、REPATABLE READ、SERIALIZABLE
* 默认是 REPEATABLE-READ -- 可重复读，事务过程中，重复查询一个数据将会得到相同的指。
