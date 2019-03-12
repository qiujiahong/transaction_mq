package com.nick.example.ticket.service;


import com.nick.example.dto.OrderDTO;
import com.nick.example.ticket.dao.TicketRepository;
import com.nick.example.ticket.domain.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "order:new",containerFactory = "msgFactory")
    public void handleTicketLock(OrderDTO dto){
        log.info("Got new order for ticket lock: {}",dto);
        int lockCount = ticketRepository.lockTicket(dto.getCustomerId(),dto.getTicketNum());
        if(lockCount == 1){
            dto.setStatus("TICKET_LOCKED");
            //https://github.com/qiujiahong/transaction_mq/blob/master/docs/assets/2019-03-10-14-26-51.png
            //下面到 order服务创建订单操作
            jmsTemplate.convertAndSend("order:locked",dto);
        }else {//锁票失败

        }

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

    @Transactional
    public int ticketLock2(OrderDTO dto){
        int lockCount = ticketRepository.lockTicket(dto.getCustomerId(),dto.getTicketNum());
        log.info("update ticket lock count :{}",lockCount);
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        return lockCount;

    }
}
