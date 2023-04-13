package com.cinema.Cinema.services;

import com.cinema.Cinema.entities.Comment;
import com.cinema.Cinema.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    public Collection<Comment> getThreeComments(){
        List comments = new ArrayList<>();
        List aux = commentRepository.findAll();
        for (int i = 0; i < 3; i++) {
            if( aux.get(i) == null){
                return comments;
            }
            comments.add(aux.get(i));
        }
        return comments;
    }

    public Collection<Comment> getComments(){
        return commentRepository.findAll();
    }
}
