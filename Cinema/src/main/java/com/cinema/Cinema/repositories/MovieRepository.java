package com.cinema.Cinema.repositories;

import com.cinema.Cinema.entities.Movie;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findMoviesByNumTicketsIsLessThan(int num);
}
