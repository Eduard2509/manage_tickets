package com.example.manage_tickets.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
public class Post implements Serializable {

    private String name;
    private double cost;
    private String status;
}
