package com.cinema.Cinema;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketController {
    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
    @GetMapping("/reviews")
    public String reviews(){
        return "reviews";
    }


}
