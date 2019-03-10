package com.nick.example.usr.web;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nick.example.dto.OrderDTO;
import com.nick.example.usr.domain.Customer;
import com.nick.example.usr.feign.OrderClient;
import com.nick.example.usr.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    @PostConstruct
    public void init(){
        Customer me = new Customer();
        me.setUserName("nick");
        me.setPassword("111111");
        me.setRole("USER");
        customerRepository.save(me);
    }
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    OrderClient orderClient;

    @PostMapping("")
    public Customer create(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @GetMapping("")
    @HystrixCommand
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    @GetMapping("/my")
    @HystrixCommand
    public Map getMyInfo(){
        Customer customer = customerRepository.findOneByUserName("nick");
        OrderDTO order = orderClient.getMyOrder(1L);
        Map result = new HashMap();
        result.put("customer",customer);
        result.put("order",order);
        return result;
    }
}
