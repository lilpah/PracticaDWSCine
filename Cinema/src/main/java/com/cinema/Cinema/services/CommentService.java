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


    public void addComment(String title,String content){
        Comment comment = new Comment(idCount.incrementAndGet(),title,content);
        commentRepository.save(comment);
    }

    public Collection<Comment> getThreeComments(){
        List comments = new ArrayList<>();
        List aux = commentRepository.findAll();
        int index = aux.size();
        if(index < 3){
            for (int i = 0; i < index; i++) {
                comments.add(aux.get(i));
            }
        }else{
            for (int i = 0; i < 3; i++) {
                comments.add(aux.get(i));
            }
        }
        return comments;
    }

    public Collection<Comment> getComments(){
        return commentRepository.findAll();
    }
}
