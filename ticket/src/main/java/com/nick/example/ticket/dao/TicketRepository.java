package com.nick.example.ticket.dao;

import com.nick.example.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByOwner(Long owner);
    Ticket findFirstByTickNum(Long ticketNum);
    Ticket findFirstByTickNumAndLockUserNot(Long ticketNum,Long lockUser);
    @Override
    @Modifying(clearAutomatically = true)   //不去做自动的优化
    Ticket save(Ticket ticket);

    //该方法被调用多次都一样。
    @Modifying
    @Query("UPDATE ticket SET lockUser = ?1 WHERE lockUser IS NULL AND tickNum=?2")
    int lockTicket(Long customerId,Long ticketNum);
}
