package com.nick.example.usr.feign;


import com.nick.example.dto.IOrderService;
import com.nick.example.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order",path = "/api/order")
public interface OrderClient extends IOrderService {
    @GetMapping("/{id}")
    OrderDTO getMyOrder(@PathVariable(name = "id") Long id);
}
