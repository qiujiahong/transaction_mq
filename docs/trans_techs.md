# 分布式事务实现模式与技术

* 分布式事务实现的几种模式
* 幂等性、唯一id。
* 分布式锁、对象

##  分布式事务的实现模式与技术

* 消息驱动模式: Message Driven,使用消息驱动模式把mq的多个服务给串起来。
* 事件溯源模式: Event Sourcing
  * 使用一个基于事件的数据源
  * 分布式系统的所有服都能访问同一个event sourcing
  * 把事件和操作的数据全部发到event sourcing中去，由它统一处理；
* tcc模式，try-confirm-cancel



## 幂等性

* 幂等性操作：执行一次和多次产生的影响，与一次是一样的；
* 方法的幂等性：使用同样的方法调用一个方法一次和多次结果是相同的；
* 接口幂等性：接口被重复调用，结果一致。


## 微服务接口的幂等性

* 重要：经常需要通过重试实现分布式事务的最终一致性；
* 一般对于get方法不用考虑幂等性；（读）
* post、put、delete方法的实现需要满足幂等性；



## 例子

* java 方法实现幂等性

```java
public class OrderService{
    Map distributeMap;
    @Transactional
    void ticketOrder(BuyTicketDTO   dto){
        String uuid= createUUIT(dto);  //创建获取数据的唯一id；
        if(!distributeMap.contains(uuid)){//本服务还没有创建这个操作
            Order order = createOrder(dto);
        }
        userService.charge(dto);//调用user微服务
    }
}
```

* sql实现幂等性

```sql
update  customer set deposit = deposit - `value`, paystatus = `PAID` 
where orderId = `orderid` AND paystatus = `UNPAID`;
```


##  系统唯一id-guid

* 分布式系统的全局唯一标示
* uuid，生成唯一id的规范；
* 用于唯一标识、处理重复消息


### 唯一id的生成办法

* 数据库的自增序列
* uuid ： 唯一id，128位，几种版本
* mongodb的object id ： 时间戳+机器id+进程id+序号
* redis的incr操作，zookeeper的节点的版本号

### 使用方式

* 自增id： 考虑安全性、部署；
* 事件有序：通过id判断创建时间；
* 长度、是否数字类型：是否创建索引。


### 分布式系统分布式对象

* redis: Redisson库,RLock,Rmap,RQueue
* zookeeper: Netflix Curator库: Lock, Queue等对象。