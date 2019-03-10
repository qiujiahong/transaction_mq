package com.nick.example.order.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String uuid;
    private Long customerId;    //订单谁发起的

    private String title;
    private Long ticketNum;     //订单是买的那一张票
    private int amount;
    private String status;
    private String reason;      //订单出错原因
}
