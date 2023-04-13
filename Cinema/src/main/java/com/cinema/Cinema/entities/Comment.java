package com.cinema.Cinema.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private String content;

    public Comment(long id, String content){
        this.id = id;
        this.content = content;
    }
}
