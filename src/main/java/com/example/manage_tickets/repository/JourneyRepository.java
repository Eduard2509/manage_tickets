package com.example.manage_tickets.repository;

import com.example.manage_tickets.model.Journey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, String> {
}
