package com.example.manage_tickets.service;

import com.example.manage_tickets.model.Journey;
import com.example.manage_tickets.model.Post;
import com.example.manage_tickets.model.Ticket;
import com.example.manage_tickets.repository.JourneyRepository;
import com.example.manage_tickets.repository.TicketRepository;
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
    RestTemplate restTemplate;

    @Mock
    JourneyRepository journeyRepository;

    @InjectMocks
    private BuyTicketService service;


    @Test
    void buyTicket_shouldCallMethodSave() {
        String name = "test";

        Journey journey = new Journey();
        journey.setId("123");
        journey.setCost(50);
        journey.setCount(15);

        Post post = mock(Post.class);
        when(post.getStatus()).thenReturn("DONE");
//        when(service.createPost(name, journey.getCost())).thenReturn(post);

        String ticketId = service.buyTicket(name, journey.getId());
        Ticket ticket = ticketRepository.findById(ticketId).get();
        verify(ticketRepository).save(ticket);
//        when(journeyRepository.findById(journey.getId())).thenReturn(Optional.of(journey));
//        String test = service.buyTicket("test", "888");
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