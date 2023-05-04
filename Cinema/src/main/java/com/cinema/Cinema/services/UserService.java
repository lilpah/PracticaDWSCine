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
    public User getUser(long id){
        return userRepository.findById(id).get();
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

        for (Ticket t : userRepository.findById(id).get().getTickets()) {
            deleteTicket(id,t.getIdTicket());
        }
        userRepository.deleteById(id);
    }

    public void modifyUser(long id, User user){
        /*users.put(id, user);
         */
        User existingUser = userRepository.findById(id).get();
        //existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        existingUser.setPass(user.getPass());
        userRepository.save(existingUser);
    }

    public void addTicket(long idUser, Ticket ticket) {
        User user = userRepository.findById(idUser).get();
        Movie movie = movieRepository.findById(ticket.getMovie().getId()).get();
        long id = user.getLastTicketAdded().incrementAndGet();
        ticket.setIdTicket(id);
        ticket.setUsers(user);
        ticket.setMovie(movie);
        ticket.setNameMovie(movie.getName());
        movie.addTicket();
        movieRepository.save(movie);
        ticketRepository.save(ticket);


        boolean t = false;
        for (Movie s :  user.getMovies()) {
            if (s.getName().equals(movie.getName())){
                t = true;
            }
        }
        if(!t){
            user.getMovies().add(movie);
            userRepository.save(user);
        }

    }



    public void modifyTicket(long idUser, Ticket newticket, long idTicket){
       /* users.get(idUser).getTickets().put(ticket.getIdTicket(), newticket);
        movies.get(ticket.getIdMovie()).addTicket();
        newticket.setNameMovie(movies.get(newticket.getIdMovie()).getName());
        */
        Ticket ticket = ticketRepository.findById(idTicket).get();
        //ticket.setNameMovie(newticket.getNameMovie());
        ticket.setNumSeat(newticket.getNumSeat());
        ticket.setMovieTime(newticket.getMovieTime());
        ticket.setMovieDate(newticket.getMovieDate());
        ticketRepository.save(ticket);

    }


    public void deleteTicket(long idUser, long idTicket){
       /* long id = users.get(idUser).getTickets().get(idTicket).getIdMovie();
        users.get(idUser).getTickets().remove(idTicket);
        movies.get(id).deleteTicket();
        */
        Ticket ticket = ticketRepository.findById(idTicket).get();
        User user = userRepository.findById(idUser).get();
        Movie movie = movieRepository.findById(ticket.getMovie().getId()).get();
        movie.deleteTicket();
        movieRepository.save(movie);
        ticketRepository.deleteById(idTicket);


        int cont = 0;
        for (Ticket s :  user.getTickets()) {
            if (s.getMovie().getName().equals(movie.getName())){
                cont++;
            }
        }
        //System.out.println(cont);
        if(cont == 0){
            /*user.getMovies().remove(movie);
            movie.getUsers().remove(user);
            movieRepository.save(movie);
            userRepository.save(user);

             */
            this.eliminarPeliculaComprada(user.getId(), movie.getId());
        }

    }

    public void eliminarPeliculaComprada(long usuarioId, long peliculaId) {
        User usuario = userRepository.findById(usuarioId).orElse(null);
        if (usuario != null) {
            Movie pelicula = usuario.getMovies().stream()
                    .filter(p -> p.getId() == peliculaId)
                    .findFirst().orElse(null);
            if (pelicula != null) {
                usuario.getMovies().remove(pelicula);
                userRepository.save(usuario);
            }
        }
    }


    public void addTicket2(long idUser, Ticket ticket, long idMovie) {
        User user = userRepository.findById(idUser).get();
        Movie movie = movieRepository.findById(idMovie).get();
        long id = user.getLastTicketAdded().incrementAndGet();
        ticket.setIdTicket(id);
        ticket.setUsers(user);
        ticket.setMovie(movie);
        ticket.setNameMovie(movie.getName());
        movie.addTicket();
        movieRepository.save(movie);
        ticketRepository.save(ticket);


        boolean t = false;
        for (Movie s :  user.getMovies()) {
            if (s.getName().equals(movie.getName())){
                t = true;
            }
        }
        if(!t){
            user.getMovies().add(movie);
            userRepository.save(user);
        }

    }
    
    public void deleteMovie( Movie movie){
        for (User u : userRepository.findAll()) {
            eliminarPeliculaComprada(u.getId(), movie.getId());
        }
    }


    public Optional<User> findByName(String name){
        return userRepository.findByName(name);
    }




}