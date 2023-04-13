package com.cinema.Cinema.restControllers;


import com.cinema.Cinema.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentRESTController {
    @Autowired
    CommentService commentService;
    //Rich Comment
    @GetMapping("/formComment")
    public String formComment(@RequestParam String comment) {
        String sanitized = comment.replaceAll("&lt;", " ").replaceAll("&gt", " ").replaceAll("'", " ").replaceAll("\"", " ").replaceAll("script", " ").replaceAll("alert", " ");
        commentService.addComment(sanitized);
        return sanitized;
    }
    @PostMapping("/formComment")
    public String formComment2(@RequestBody String comment) {
        String sanitized = comment.replaceAll("&lt;", " ").replaceAll("&gt", " ").replaceAll("'", " ").replaceAll("\"", " ").replaceAll("script", " ").replaceAll("alert", " ");
        return sanitized;
    }
}
