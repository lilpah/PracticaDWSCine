package com.cinema.Cinema.entities;

import com.cinema.Cinema.entities.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    private String name;
    private String surname;
    private String pass;
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = -1;
    private AtomicLong lastTicketAdded;
    // private Boolean admin; -> When we are allowed to use databases, add



    /*

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   /* @JoinTable(
            name="tickets_to_buy",
            joinColumns= @JoinColumn(name = "user_id"),
            inverseJoinColumns= @JoinColumn(name="ticket_id")
    )

    @JsonManagedReference
    private List<Ticket> tickets = new ArrayList<>();

    /*
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonManagedReference
    @JsonIgnore
    private List<Ticket> tickets = new ArrayList<>();
     */

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "users_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies = new ArrayList<>();



    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "users")
    @JsonIgnore
    private List<Ticket> tickets = new ArrayList<>();





    public User(String name, String surname, String pass, String email) {
        this.name = name;
        this.surname = surname;
        this.pass = pass;
        this.email = email;
        this.lastTicketAdded = new AtomicLong();
    }




   public Ticket getATicket(long id){
        for (Ticket t :
                tickets) {
            if(t.getIdTicket()== id) return t;
        }
        return null;
    }
    public int deleteTicket(long idTicket){
        for (Ticket t :
                tickets) {
            if(t.getIdTicket()== idTicket) {
                tickets.remove(t);
                return 0;
            }
        }
        return -1;
    }


    public int searchTicket(long idTicket){
        int i =0, explorer = -1;
        for (Ticket t : tickets) {
            if(t.getIdTicket()== idTicket) explorer = i;
            i++;
        }
        return explorer;
    }

}
