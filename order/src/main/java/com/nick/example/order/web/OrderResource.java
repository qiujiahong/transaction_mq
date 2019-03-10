package com.nick.example.order.web;

import com.nick.example.dto.IOrderService;
import com.nick.example.dto.OrderDTO;
import com.nick.example.order.dao.OrderRepository;
import com.nick.example.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderResource implements IOrderService {

    @PostConstruct
    public void init() {
        Order order = new Order();
        order.setTitle("My order");
        order.setDetail("This is my order");
        order.setAmount(100);
        orderRepository.save(order);

    }

    @Autowired
    OrderRepository orderRepository;

    @PostMapping("")
    public Order create(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping("")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO getMyOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setTitle(order.getTitle());
            dto.setDetail(order.getDetail());
            return dto;
        }
        else
            return null;
    }
}
