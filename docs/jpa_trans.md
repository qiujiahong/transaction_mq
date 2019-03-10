#  jpa事务

演示jpa事务管理  
* 代码
* 标签的方式
* 使用h2数据库


## 使用代码实现事务


```java
@Slf4j
@Service
public class CustomerServiceInCode {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public Customer create(Customer customer){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus ts = platformTransactionManager.getTransaction(def);

        try {
            customerRepository.save(customer);
            platformTransactionManager.commit(ts);
            return customer;
        }catch (Exception e){
            platformTransactionManager.rollback(ts);
            throw e;
        }
    }
}

```

## 使用注解实现事务

```java

@Slf4j
@Service
public class CustomerServiceInAnnotation {

    @Autowired
    CustomerRepository customerRepository;


    @Transactional
    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }
}
```