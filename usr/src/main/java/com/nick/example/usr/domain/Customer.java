package com.nick.example.usr.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name="customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="user_name")
    private String userName;

    private String password;

    private String role;


}
