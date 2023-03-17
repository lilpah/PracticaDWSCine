package com.cinema.Cinema.user;

import com.cinema.Cinema.ticket.Ticket;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<User, HashMap<Integer, Ticket>> ticketsByUser; // Integer is for the id of each ticket that each user has

}
