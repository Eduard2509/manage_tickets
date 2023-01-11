package com.example.manage_tickets.service;

import com.example.manage_tickets.model.Journey;
import com.example.manage_tickets.model.Post;
import com.example.manage_tickets.model.Ticket;
import com.example.manage_tickets.repository.JourneyRepository;
import com.example.manage_tickets.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuyTicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @Mock
    JourneyRepository journeyRepository;

    @InjectMocks
    private BuyTicketService service;


    @Test
    void buyTicket_shouldReturnNoTickets() {
        String noTicket = "There are no tickets";
        String name = "test";
        Journey journey = new Journey();
        journey.setCount(0);
        when(journeyRepository.findById(journey.getId())).thenReturn(Optional.of(journey));
        String buyTicket = service.buyTicket(name, journey.getId());
        Assertions.assertEquals(noTicket, buyTicket);
    }

    @Test
    void checkStatusPayment_shouldCallMethodSave() {
        Journey journey = new Journey();
        journey.setCount(15);
        journey.setCost(50);
        Ticket ticket = mock(Ticket.class);
        when(ticket.getJourney()).thenReturn(journey);
        when(ticket.getStatus()).thenReturn("FAILED");
        service.checkStatusPayment(ticket);
        verify(ticketRepository).save(ticket);
    }
}