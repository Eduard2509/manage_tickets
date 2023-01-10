package com.example.manage_tickets.controller;

import com.example.manage_tickets.service.BuyTicketService;
import com.example.manage_tickets.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    BuyTicketService buyTicketService;
    InformationService informationService;

    @Autowired
    public MainController(BuyTicketService buyTicketService, InformationService informationService) {
        this.buyTicketService = buyTicketService;
        this.informationService = informationService;
    }

    @PostMapping("/buy")
    public String buyTicket(String name, String journeyId) {
        System.out.println(name + "  " + journeyId);
        return buyTicketService.buyTicket(name, journeyId);
    }

    @GetMapping("/info")
    public String getInfoByTicket(String idTicket) {
        return informationService.getInformationFromTicket(idTicket);
    }

}
