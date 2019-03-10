package com.nick.example.order.dao;

import com.nick.example.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findOneByTitle(String title);
}
