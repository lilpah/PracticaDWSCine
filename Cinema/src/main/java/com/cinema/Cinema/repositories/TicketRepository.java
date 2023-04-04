package com.cinema.Cinema.repositories;

import com.cinema.Cinema.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
