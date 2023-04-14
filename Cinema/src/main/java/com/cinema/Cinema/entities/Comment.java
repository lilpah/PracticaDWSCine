package com.cinema.Cinema.entities;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String content;
    private AtomicLong idCount = new AtomicLong();

    public Comment(String title, String content){
        this.id = idCount.incrementAndGet();
        this.title = title;
        this.content = content;
    }
}
