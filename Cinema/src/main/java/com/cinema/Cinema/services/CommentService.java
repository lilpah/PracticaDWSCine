package com.cinema.Cinema.services;

import com.cinema.Cinema.entities.Comment;
import com.cinema.Cinema.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;


@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    private AtomicLong idCount = new AtomicLong();


    public void addComment(String string){
        Comment comment = new Comment(idCount.incrementAndGet(),string);
        commentRepository.save(comment);
    }
    
}
