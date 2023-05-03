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
    @GetMapping("/addUsers")
    public String addUser(Model model){
        model.addAttribute("users", userService.getUsers());
        return "addUsers";
    }

    @GetMapping("/userAdded")
    public String formUser(Model model,  @RequestParam String pass, @RequestParam String name, @RequestParam String surname,@RequestParam String email){
        userService.addUser(new User(StringEscapeUtils.escapeHtml4(name),StringEscapeUtils.escapeHtml4(surname),StringEscapeUtils.escapeHtml4(passwordEncoder.encode(pass)),StringEscapeUtils.escapeHtml4(email),"USER"));
        model.addAttribute("user", StringEscapeUtils.escapeHtml4(name));
        return "redirect:login";
    }

    @GetMapping("/user/{id}/modifyUser")
    public String modifyUser(Model model, @PathVariable Long id){
        model.addAttribute("name", userService.getUser(id).getName());
        model.addAttribute("surname", userService.getUser(id).getSurname());
        model.addAttribute("pass", userService.getUser(id).getPass());
        model.addAttribute("email", userService.getUser(id).getEmail());
        return "modifyUser";
    }

    @GetMapping("/user/{id}/userModified")
    public String userModified(Model model,  @RequestParam String pass, @RequestParam String name, @RequestParam String surname,@PathVariable Long id){
        /*userService.getUser(id).get().setName(name);
        userService.getUser(id).get().setSurname(surname);
        userService.getUser(id).get().setPass(pass);
         */
        userService.modifyUser(id, new User(name, surname, pass, userService.getUser(id).getEmail()));
        model.addAttribute("name", userService.getUser(id).getName());
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





/*

--------------------------------------------









@GetMapping("/showUsers")
public String showUser(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "usersTable";
        }

@GetMapping("/user/{id}")
public String user(Model model, @PathVariable Long id){
        model.addAttribute("name", userRepository.findById(id).get().getName());
        return "userIndex";
        }

@GetMapping("/deleteUsers")
public String deleteUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "deleteUsers";
        }

@GetMapping("/deleteUsers/{id}")
public String deleteUser(Model model, @PathVariable Long id){
        model.addAttribute("user", userRepository.findById(id).get().getName());
        userRepository.deleteById(id);
        return "userDeleted";
        }
@GetMapping("/addUsers")
public String addUser(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "addUsers";
        }

@GetMapping("/userAdded")
public String formUser(Model model,  @RequestParam String pass, @RequestParam String name, @RequestParam String surname,@RequestParam String email){
        userRepository.save(new User(name,surname,pass,email));
        model.addAttribute("user",name);
        return "userAdded";
        }

@GetMapping("/user/{id}/modifyUser")
public String modifyUser(Model model, @PathVariable Long id){
        model.addAttribute("name", userRepository.findById(id).get().getName());
        model.addAttribute("surname", userRepository.findById(id).get().getSurname());
        model.addAttribute("pass", userRepository.findById(id).get().getPass());
        model.addAttribute("email", userRepository.findById(id).get().getEmail());
        return "modifyUser";
        }

@GetMapping("/user/{id}/userModified")
public String userModified(Model model,  @RequestParam String pass, @RequestParam String name, @RequestParam String surname,@PathVariable Long id){
        userRepository.findById(id).get().setName(name);
        userRepository.findById(id).get().setSurname(surname);
        userRepository.findById(id).get().setPass(pass);
        model.addAttribute("name", userRepository.findById(id).get().getName());
        return "userModified";
        }
        }
*/