package com.cinema.Cinema.movie;

import com.cinema.Cinema.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieRESTController {
    @Autowired
    UserService userService;




}
