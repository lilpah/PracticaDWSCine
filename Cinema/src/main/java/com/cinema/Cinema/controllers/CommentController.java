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
    public String formComment(@RequestParam String title,@RequestParam String comment, Model model){

        String sanitizedComment = comment.replaceAll("&lt;", " ").replaceAll("&gt", " ").replaceAll("img", " ").replaceAll("'", " ").replaceAll("\"", " ").replaceAll("script", " ").replaceAll("alert", " ");
        String sanitizedTitle = title.replaceAll("&lt;", " ").replaceAll("&gt", " ").replaceAll("img", " ").replaceAll("'", " ").replaceAll("\"", " ").replaceAll("script", " ").replaceAll("alert", " ").replaceAll(">", " ").replaceAll("<", " ");
        commentService.addComment(sanitizedTitle,sanitizedComment);
        model.addAttribute("comments", commentService.getThreeComments());
        return "contact";
    }

    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("comments", commentService.getThreeComments());
        return "contact";
    }

    @GetMapping("/contact/showComments")
    public String showComments(Model model){
        model.addAttribute("comments",commentService.getComments());
        return "showComments";
    }
}
