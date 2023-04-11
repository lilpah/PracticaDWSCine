package com.cinema.Cinema.repositories;

import com.cinema.Cinema.entities.Ticket;
import com.cinema.Cinema.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository

public interface TicketRepository extends JpaRepository<Ticket,Long> {

   /* @Query
    public List<Ticket> findTicketByNameMovie(Ticket restaurant, String name) {
        TypedQuery<Ticket> query= entityManager.createQuery
                ("SELECT d FROM Dishes d WHERE d.restaurant=:restaurant AND (d.name LIKE CONCAT('%',:name,'%'))",Dishes.class);
        return query.setParameter("name",name).setParameter("restaurant",restaurant).getResultList();
    }

    */

    @Query("SELECT t.users FROM Ticket t WHERE t.movie.id = :movieId")
    List<User> findByMovieId(@Param("movieId") Long movieId);
}
