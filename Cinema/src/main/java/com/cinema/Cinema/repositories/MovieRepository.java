package com.cinema.Cinema.repositories;

import com.cinema.Cinema.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
}
