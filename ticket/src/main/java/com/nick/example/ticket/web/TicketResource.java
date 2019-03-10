package com.nick.example.ticket.web;

import com.nick.example.dto.OrderDTO;
import com.nick.example.ticket.dao.TicketRepository;
import com.nick.example.ticket.domain.Ticket;
import com.nick.example.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket")
public class TicketResource  {

    @PostConstruct
    public void init() {
        if(ticketRepository.count() > 0 ){
            return;
        }
        Ticket ticket = new Ticket();
        ticket.setName("Num.01");
        ticket.setTickNum(100L);
        ticketRepository.save(ticket);
    }

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;


    @PostMapping("/lock")
    public Ticket lock(@RequestBody  OrderDTO orderDTO){
        return ticketService.ticketLock(orderDTO);
    }

    @PostMapping("/lock2")
    public int lock2(@RequestBody  OrderDTO orderDTO){
        return ticketService.ticketLock2(orderDTO);
    }

}
