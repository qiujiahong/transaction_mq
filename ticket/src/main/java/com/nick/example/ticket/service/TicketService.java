package com.nick.example.ticket.service;


import com.nick.example.dto.OrderDTO;
import com.nick.example.ticket.dao.TicketRepository;
import com.nick.example.ticket.domain.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @JmsListener(destination = "order:new",containerFactory = "msgFactory")
    public void handleTicketLock(OrderDTO dto){
        log.info("Got new order for ticket lock: {}",dto);

    }

    @Transactional
    public Ticket ticketLock(OrderDTO dto)  {
        Ticket ticket = ticketRepository.findFirstByTickNum(dto.getTicketNum());
        ticket.setLockUser(dto.getCustomerId());
        ticketRepository.save(ticket);
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        return ticket;
    }
}
