package com.cinema.Cinema.restControllers;


import com.cinema.Cinema.entities.Comment;
import com.cinema.Cinema.entities.Ticket;
import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.services.CommentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class CommentRESTController {
    @Autowired
    CommentService commentService;
    //Rich Comment
    @JsonView(Comment.CommentView.class)
    @GetMapping("/formComment")
    public Comment formComment(@RequestParam String title, @RequestParam String comment) {
        String sanitizedComment = comment.replaceAll("&lt;", " ").replaceAll("&gt", " ").replaceAll("img", " ").replaceAll("'", " ").replaceAll("\"", " ").replaceAll("script", " ").replaceAll("alert", " ").replaceAll("onerror", " ");
        String sanitizedTitle = title.replaceAll("&lt;", " ").replaceAll("&gt", " ").replaceAll("img", " ").replaceAll("'", " ").replaceAll("\"", " ").replaceAll("script", " ").replaceAll("alert", " ").replaceAll("onerror", " ");
        Comment comentario = new Comment(sanitizedTitle, sanitizedComment);
        commentService.addComment2(comentario);
        return comentario;
    }

    @JsonView(Comment.CommentView.class)
    @PostMapping("/formComment")
    public Comment formComment2(@RequestBody Comment comment) {
        comment.setContent(comment.getContent().replaceAll("&lt;", " ").replaceAll("&gt", " ").replaceAll("img", " ").replaceAll("'", " ").replaceAll("\"", " ").replaceAll("script", " ").replaceAll("alert", " ").replaceAll("onerror", " "));
        comment.setTitle(comment.getTitle().replaceAll("&lt;", " ").replaceAll("&gt", " ").replaceAll("img", " ").replaceAll("'", " ").replaceAll("\"", " ").replaceAll("script", " ").replaceAll("alert", " ").replaceAll("onerror", " "));
        commentService.addComment2(comment);
        return comment;
    }

    @JsonView(Comment.CommentView.class)
    @GetMapping("/showComment/{idComment}")
    public Comment showComment(@PathVariable long idComment) {
        return commentService.getComment(idComment);
    }


    @JsonView(Comment.CommentView.class)
    @PostMapping("/showComment/{idComment}")
    public Comment showComment2(@PathVariable long idComment){
        return commentService.getComment(idComment);
    }


    @JsonView(Comment.CommentView.class)
    @GetMapping("/showAllComments")
    public ResponseEntity<Collection<Comment>> showAllComments(){
        if(commentService.getComments() == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<>(commentService.getComments(), HttpStatus.OK);
        }

    }

    @JsonView(Comment.CommentView.class)
    @PostMapping("/showAllComments")
    public ResponseEntity<Collection<Comment>> showAllComments2(){
        if(commentService.getComments() == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<>(commentService.getComments(), HttpStatus.OK);
        }

    }


    
}
