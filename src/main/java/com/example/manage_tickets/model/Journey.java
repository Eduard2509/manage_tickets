package com.example.manage_tickets.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String fromGo;
    private String toGo;
    private Timestamp departureTime;
    private double cost;
    private int count;

    @OneToMany(mappedBy = "journey",
            fetch = FetchType.EAGER)
    private List<Ticket> tickets;
}
