package com.example.manage_tickets.repository;

import com.example.manage_tickets.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, String> {
}
