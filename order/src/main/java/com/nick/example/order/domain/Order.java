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

    private String title;
    private String detail;
    private int amount;
}
