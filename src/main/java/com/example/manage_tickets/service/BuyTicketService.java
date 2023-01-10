package com.example.manage_tickets.service;

import com.example.manage_tickets.model.Journey;
import com.example.manage_tickets.model.Post;
import com.example.manage_tickets.model.Ticket;
import com.example.manage_tickets.repository.JourneyRepository;
import com.example.manage_tickets.repository.TicketRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class BuyTicketService {

    private RestTemplate restTemplate;
    private JourneyRepository journeyRepository;
    private TicketRepository ticketRepository;

    public BuyTicketService(RestTemplate restTemplate, JourneyRepository journeyRepository, TicketRepository ticketRepository) {
        this.restTemplate = restTemplate;
        this.journeyRepository = journeyRepository;
        this.ticketRepository = ticketRepository;
    }

    public Post createPost(String name, double price) {
        String url = "http://localhost:8081/";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("cost", price);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<Post> response = this.restTemplate.postForEntity(url, entity, Post.class);

        return response.getBody();
    }

    public String buyTicket(String name, String idJourney) {
        Ticket ticket = new Ticket();
        Journey journey = journeyRepository.findById(idJourney).get();
        if (journey.getCount() > 0) {
            journey.setCount(journey.getCount() - 1);
            Post post = createPost(name, journey.getCost());
            ticket.setName(name);
            ticket.setJourney(journey);
            ticket.setStatus(post.getStatus());
            ticketRepository.save(ticket);
            checkStatusPayment(ticket);
            return ticket.getId();
        } else {
            return "There are no tickets";
        }
    }

    public void checkStatusPayment(Ticket ticket) {
        if (ticket.getStatus().equals("FAILED")) {
            ticket.getJourney().setCount(ticket.getJourney().getCount() + 1);
            ticketRepository.save(ticket);
        }
    }
}
