package com.cinema.Cinema.repositories;
import com.cinema.Cinema.entities.Ticket;
import com.cinema.Cinema.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /*@Modifying
    @Query("DELETE FROM users_movies um WHERE um.user.id = :userId AND um.movie.id = :movieId")
    void deleteByUserIdAndMovieId(@Param("userId") Long userId, @Param("movieId") Long movieId);

     */

    Optional<User> findByName(String name);

}
