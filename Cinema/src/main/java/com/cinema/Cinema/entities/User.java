package com.cinema.Cinema.entities;

import com.cinema.Cinema.entities.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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

    @ManyToMany
    @JoinTable(
            name="movies_to_watch",
            joinColumns= @JoinColumn(name = "user_id"),
            inverseJoinColumns= @JoinColumn(name="movie_id")
    )
    @JsonManagedReference
    private List<Movie> movies = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonManagedReference
    @JsonIgnore
    private List<Ticket> tickets = new ArrayList<>();
    public User(String name, String surname, String pass, String email) {
        this.name = name;
        this.surname = surname;
        this.pass = pass;
        this.email = email;
        this.lastTicketAdded = new AtomicLong();
    }





}
