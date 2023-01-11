package com.example.manage_tickets.service;

import com.example.manage_tickets.model.Journey;
import com.example.manage_tickets.model.Ticket;
import com.example.manage_tickets.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InformationServiceTest {

    @Mock
    TicketRepository repository;

    @InjectMocks
    InformationService service;

    @Test
    void getInformationFromTicket_shouldCallMethodFindById() {

        Journey journey = new Journey();
        Ticket ticket = new Ticket();
        ticket.setId("123");
        ticket.setJourney(journey);

        when(repository.findById("123")).thenReturn(Optional.of(ticket));

        service.getInformationFromTicket("123");
        verify(service.ticketRepository).findById(ticket.getId());

    }
}