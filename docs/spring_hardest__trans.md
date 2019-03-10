# spring 最大努力一次提交

* jms-db
* ActiveMq , MySQL
* 最大努力一次提交，TransactionAwareConnectionFactoryProxy



```java
@Configuration
public class JmsConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        TransactionAwareConnectionFactoryProxy proxy = new TransactionAwareConnectionFactoryProxy();
        proxy.setTargetConnectionFactory(cf);
        proxy.setSynchedLocalTransactionAllowed(true);
        return proxy;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }
}

```