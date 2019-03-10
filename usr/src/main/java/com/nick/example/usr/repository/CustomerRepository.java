package com.nick.example.usr.repository;

import com.nick.example.usr.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findOneByUserName(String userName);
}
