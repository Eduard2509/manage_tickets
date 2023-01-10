package com.example.manage_tickets.service;

import com.example.manage_tickets.model.Ticket;
import com.example.manage_tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationService {

    TicketRepository ticketRepository;

    @Autowired
    public InformationService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public String getInformationFromTicket(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        StringBuilder sb = new StringBuilder("Information from ticket: " + "\n");
        sb.append("Name:" + ticket.getName() + "\n");
        sb.append("From: " + ticket.getJourney().getFromGo() + "\n");
        sb.append("To: " + ticket.getJourney().getToGo() + "\n");
        sb.append("Departure Time: " + ticket.getJourney().getDepartureTime() + "\n");
        sb.append("Cost: " + ticket.getJourney().getCost() + "\n");
        sb.append("Status: " + ticket.getStatus());
        return sb.toString();
    }
}

