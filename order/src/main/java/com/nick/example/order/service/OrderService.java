package com.nick.example.order.service;


import com.nick.example.dto.OrderDTO;
import com.nick.example.order.dao.OrderRepository;
import com.nick.example.order.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Slf4j
@Service
public class OrderService {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    @JmsListener(destination = "order:locked",containerFactory = "msgFactory")
    public void handleOrderNew(OrderDTO dto){
        log.info("Got new order to create: {}",dto);
        if(orderRepository.findOneByUuid(dto.getUuid()) != null){//该订单已经创建
            log.info("Msg already processed.{}",dto);
        }else {
            Order order = createOrder(dto);
            orderRepository.save(order);
        }
        dto.setStatus("NEW");
        jmsTemplate.convertAndSend("order:pay",dto);
    }


    private Order createOrder (OrderDTO dto){
        Order order = new Order();
        order.setUuid(dto.getUuid());
        order.setAmount(dto.getAmount());
        order.setTitle(dto.getTitle());
        order.setCustomerId(dto.getCustomerId());
        order.setTicketNum(dto.getTicketNum());
        order.setStatus("NEW");
        order.setCreatedDate(ZonedDateTime.now());
        return order;
    }

}
