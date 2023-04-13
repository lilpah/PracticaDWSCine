package com.cinema.Cinema.repositories;

import com.cinema.Cinema.entities.Comment;
import com.cinema.Cinema.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
}
