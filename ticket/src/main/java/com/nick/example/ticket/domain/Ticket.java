package com.nick.example.ticket.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Long owner;
    private Long lockUser;

    private Long tickNum;
}
