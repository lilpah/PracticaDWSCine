package com.cinema.Cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class CinemaController {
    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
    @GetMapping("reviews")
    public String reviewsPage(){
        return "reviews";
    }
    @GetMapping("contact")
    public String contactPage(){
        return "contact";
    }
    @GetMapping("error")
    public String errorPage(){
        return "error";
    }
    @GetMapping("tickets")
    public String ticketPage(){
        return "tickets";
    }

}
