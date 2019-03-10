package com.nick.example.ticket.dao;

import com.nick.example.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findOneByOwner(Long owner);
}
