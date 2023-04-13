package com.cinema.Cinema.controllers;

import com.cinema.Cinema.repositories.CommentRepository;
import com.cinema.Cinema.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/contact/formComment")
    public String formComment(@RequestParam String comment, Model model){
        String sanitized = comment.replaceAll("&lt;", " ").replaceAll("&gt", " ").replaceAll("img", " ").replaceAll("'", " ").replaceAll("\"", " ").replaceAll("script", " ").replaceAll("alert", " ");
        commentService.addComment(sanitized);
        model.addAttribute("comment", commentService.getThreeComments());
        return "contact";
    }

    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("comment", commentService.getThreeComments());
        return "contact";
    }

    @GetMapping("/contact/showComments")
    public String showComments(Model model){
        model.addAttribute("comments",commentService.getComments());
        return "showComments";
    }
}
