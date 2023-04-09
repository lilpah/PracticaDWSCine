package com.cinema.Cinema.services;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.entities.Ticket;
import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.repositories.MovieRepository;
import com.cinema.Cinema.repositories.TicketRepository;
import com.cinema.Cinema.repositories.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Getter
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    MovieRepository movieRepository;

    //private Map<Long, Movie> movies = new ConcurrentHashMap<>();
    private AtomicLong lastMovie = new AtomicLong();

    // private Map<Long, User> users = new ConcurrentHashMap<>(); // Long is for the id of each ticket that each user has

    private AtomicLong lastId = new AtomicLong();

    /*
       public UserService(){
            addUser(new User("User 1","Surname User 1","P@ssW0rd_1","user1@lacartelera.com"));
            addUser(new User("User 2","Surname User 2","P@ssW0rd_2","user2@lacartelera.com"));
            addUser(new User("User 3","Surname User 3","P@ssW0rd_3","user3@lacartelera.com"));


            addMovie(new Movie("Creed","Action"));
            addMovie(new Movie("Scream VI","Terror"));
            addMovie(new Movie("Mari(dos)","Comedy"));
            addMovie(new Movie("Watcher","Thriller"));
            addMovie(new Movie("As bestas","Mystery"));

            movieRepository.save(new Movie("Creed","Action"));
            movieRepository.save(new Movie("Scream VI","Terror"));
            movieRepository.save(new Movie("Mari(dos)","Comedy"));
            movieRepository.save(new Movie("Watcher","Thriller"));
            movieRepository.save(new Movie("As bestas","Mystery"));

            addTicket(1, new Ticket(1, 24, "21:24", "3/03/2023"));
            addTicket(2, new Ticket(2, 14, "22:00", "5/03/2023"));
            addTicket(3, new Ticket(3, 56, "15:30", "1/08/2023"));
        }
        */
    public Collection<Movie> getMovies(){
        return movieRepository.findAll();
    }
    public Optional<User> getUser(long id){
        return userRepository.findById(id);
    }
    public Collection<User> getUsers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        /*long id = lastId.incrementAndGet();
        user.setId(id);
        users.put(id,user);
         */
        long id = lastId.incrementAndGet();
        user.setId(id);
        userRepository.save(user);
    }

    public void deleteUser(long id){
        /*lastId.decrementAndGet();
        users.remove(id);
         */
        lastId.decrementAndGet();
        userRepository.deleteById(id);
    }

    public void modifyUser(long id, User user){
        /*users.put(id, user);
         */
        User existingUser = userRepository.findById(id).get();
        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        existingUser.setPass(user.getPass());
        userRepository.save(existingUser);
    }

    public void addTicket(long idUser, Ticket ticket){
        //long id = users.get(idUser).getLastTicketAdded().incrementAndGet();
        //ticket.setIdTicket(id);
        // users.get(idUser).getTickets().put(id,ticket);
        //movies.get(ticket.getIdMovie()).addTicket();
        //ticket.setNameMovie(movies.get(ticket.getIdMovie()).getName());
        long id = userRepository.findById(idUser).get().getLastTicketAdded().incrementAndGet();
        ticket.setIdTicket(id);
        userRepository.findById(idUser).get().getTickets().add(ticket);
        movieRepository.findById(ticket.getMovie().getId()).get().addTicket();

    }


    public void modifyTicket(long idUser, Ticket newticket, Ticket ticket){
       /* users.get(idUser).getTickets().put(ticket.getIdTicket(), newticket);
        movies.get(ticket.getIdMovie()).addTicket();
        newticket.setNameMovie(movies.get(newticket.getIdMovie()).getName());
        */
        newticket.setIdTicket(ticket.getIdTicket());
        userRepository.findById(idUser).get().getTickets().remove(ticket);
        userRepository.findById(idUser).get().getTickets().add(newticket);
    }


    public void deleteTicket(long idUser, long idTicket){
       /* long id = users.get(idUser).getTickets().get(idTicket).getIdMovie();
        users.get(idUser).getTickets().remove(idTicket);
        movies.get(id).deleteTicket();
        */
        int num = userRepository.findById(idUser).get().searchTicket(idTicket);
        if(num != -1) userRepository.findById(idUser).get().deleteTicket(idTicket);
    }


    public void addMovie(Movie movie){
        long idMovie = lastMovie.incrementAndGet();
        movie.setId(idMovie);
        //movies.put(idMovie,movie);
        movieRepository.save(movie);

    }



}