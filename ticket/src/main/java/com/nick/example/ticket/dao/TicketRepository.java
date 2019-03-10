package com.nick.example.ticket.dao;

import com.nick.example.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByOwner(Long owner);
    Ticket findFirstByTickNum(Long ticketNum);

    @Override
    @Modifying(clearAutomatically = true)   //不去做自动的优化
    Ticket save(Ticket ticket);
}
