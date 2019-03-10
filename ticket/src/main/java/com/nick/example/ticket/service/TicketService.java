package com.nick.example.ticket.service;


import com.nick.example.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TicketService {

    @JmsListener(destination = "order:new",containerFactory = "msgFactory")
    public void handleTicketLock(OrderDTO dto){
        log.info("Got new order for ticket lock: {}",dto);

    }

    public void ticketLock(OrderDTO dto){

    }
}
