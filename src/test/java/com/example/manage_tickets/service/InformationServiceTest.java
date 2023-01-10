package com.example.manage_tickets.service;

import com.example.manage_tickets.model.Journey;
import com.example.manage_tickets.model.Ticket;
import com.example.manage_tickets.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;


class InformationServiceTest {

    @Mock
    TicketRepository repository;

    @InjectMocks
    InformationService service;


    @Test
    void getInformationFromTicket_shouldCallMethodFindById() {
        Journey journey = new Journey();
        Ticket ticket = new Ticket();
        ticket.setName("Test Ticket");
        ticket.setId("123");
        ticket.setJourney(journey);
        ticket.setStatus("DONE");

        service.getInformationFromTicket(ticket.getId());
        verify(service.ticketRepository).findById(ticket.getId());

    }
}