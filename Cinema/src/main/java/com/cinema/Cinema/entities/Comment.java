package com.cinema.Cinema.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {

    public interface CommentView{}

    @JsonView(Comment.CommentView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonView(Comment.CommentView.class)
    private String title;
    @JsonView(Comment.CommentView.class)
    private String content;
    private AtomicLong idCount = new AtomicLong();

    public Comment(String title, String content){
        this.id = idCount.incrementAndGet();
        this.title = title;
        this.content = content;
    }
}
