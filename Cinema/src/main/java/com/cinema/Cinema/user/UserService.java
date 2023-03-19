package com.cinema.Cinema.user;

import com.cinema.Cinema.movie.Movie;
import com.cinema.Cinema.movie.MovieService;
import com.cinema.Cinema.ticket.Ticket;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Getter
public class UserService {

    private Map<Long, Movie> movies = new ConcurrentHashMap<>();
    private AtomicLong lastMovie = new AtomicLong();

    private Map<Long, User> users = new ConcurrentHashMap<>(); // Long is for the id of each ticket that each user has

    private AtomicLong lastId = new AtomicLong();



    public UserService(){
        addUser(new User("nacho","chove","1234","tumadre@gamil"));
        addUser(new User("sanmi","panda","1234","tumadress@gamil"));
        addMovie(new Movie("Creed","Action"));
        addMovie(new Movie("As bestas","Drama"));
        addTicket(1, new Ticket(1, 24, "21:24", "3/03/2023"));
        addTicket(2, new Ticket(2, 14, "21:24", "3/03/2023"));
    }

    public void addUser(User user){
        long id = lastId.incrementAndGet();
        user.setId(id);
        users.put(id,user);
    }
    public void addTicket(long idUser, Ticket ticket){
        long id = users.get(idUser).getLastTicketAdded().incrementAndGet();
        users.get(idUser).getTickets().put(id,ticket);
        movies.get(ticket.getIdMovie()).addTicket();
        ticket.setNameMovie(movies.get(ticket.getIdMovie()).getName());
    }
    public void addMovie(Movie movie){
        long idMovie = lastMovie.incrementAndGet();
        movie.setIdMovie(idMovie);
        movies.put(idMovie,movie);
    }




}
