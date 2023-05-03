package com.cinema.Cinema.controllers;

import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.repositories.UserRepository;
import com.cinema.Cinema.services.UserService;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/admin/showUsers")
    public String showUser(Model model){
        model.addAttribute("users", userService.getUsers());
        return "usersTable";
    }

    @GetMapping("/admin/user/{id}")
    public String user(Model model, @PathVariable Long id){
        model.addAttribute("name", userService.getUser(id).getName());
        return "ticketsUser";
    }

    @GetMapping("/user")
    public String user(Model model, HttpServletRequest request){

        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        model.addAttribute("username", user.getName());
        model.addAttribute("admin", request.isUserInRole("ADMIN"));



        model.addAttribute("name", name);
        return "userIndex";
    }



    @GetMapping("/admin/deleteUsers")
    public String deleteUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        return "deleteUsers";
    }

    @GetMapping("/admin/deleteUsers/{id}")
    public String deleteUser(Model model, @PathVariable Long id){
        model.addAttribute("user", userService.getUser(id).getName());
        userService.deleteUser(id);
        return "userDeleted";
    }
    @GetMapping("/register")
    public String addUser(Model model){
        model.addAttribute("users", userService.getUsers());
        return "addUsers";
    }

    @GetMapping("/userAdded")
    public String formUser(Model model,  @RequestParam String pass, @RequestParam String name, @RequestParam String surname,@RequestParam String email){
        userService.addUser(new User(StringEscapeUtils.escapeHtml4(name),StringEscapeUtils.escapeHtml4(surname),StringEscapeUtils.escapeHtml4(passwordEncoder.encode(pass)),StringEscapeUtils.escapeHtml4(email),"USER"));
        model.addAttribute("user", StringEscapeUtils.escapeHtml4(name));
        return "redirect: login";
    }




 // no va la zorra
   /* @PostMapping("/register")
    public String addUser(Model model, @RequestParam("name") String name, @RequestParam("email") String surname, @RequestParam("email") String email, @RequestParam("password") String pass){
       // userService.addUser(user);
        userService.addUser(new User(StringEscapeUtils.escapeHtml4(name),StringEscapeUtils.escapeHtml4(surname),StringEscapeUtils.escapeHtml4(passwordEncoder.encode(pass)),StringEscapeUtils.escapeHtml4(email),"USER"));
        model.addAttribute("user", StringEscapeUtils.escapeHtml4(name));
        return "redirect:/login";
    }

    */





    @GetMapping("/user/modifyUser")
    public String modifyUser(Model model, HttpServletRequest request){
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());


        model.addAttribute("name", userService.getUser(user.getId()).getName());
        model.addAttribute("surname", userService.getUser(user.getId()).getSurname());
        model.addAttribute("pass", userService.getUser(user.getId()).getPass());
        model.addAttribute("email", userService.getUser(user.getId()).getEmail());
        return "modifyUser";
    }

    @GetMapping("/user/userModified")
    public String userModified(Model model, @RequestParam String pass, @RequestParam String name, @RequestParam String surname, HttpServletRequest request){
        /*userService.getUser(id).get().setName(name);
        userService.getUser(id).get().setSurname(surname);
        userService.getUser(id).get().setPass(pass);
         */

        String uda = request.getUserPrincipal().getName();
        User user = userRepository.findByName(uda).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        user.setName(name);
        user.setSurname(surname);
        user.setPass(pass);
        userService.modifyUser(user.getId(), user);
        model.addAttribute("name", userService.getUser(user.getId()).getName());
        return "userModified";

    }

    @GetMapping("/admin")
    public String admin(Model model, HttpServletRequest request){

        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        model.addAttribute("name", name);
        return "admin";
    }




}


