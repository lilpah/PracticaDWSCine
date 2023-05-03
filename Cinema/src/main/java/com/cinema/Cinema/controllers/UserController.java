package com.cinema.Cinema.controllers;

import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.repositories.UserRepository;
import com.cinema.Cinema.services.UserService;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/showUsers")
    public String showUser(Model model){
        model.addAttribute("users", userService.getUsers());
        return "usersTable";
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable Long id){
        model.addAttribute("name", userService.getUser(id).getName());
        return "userIndex";
    }

    @GetMapping("/deleteUsers")
    public String deleteUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        return "deleteUsers";
    }

    @GetMapping("/deleteUsers/{id}")
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
    public String formUser(Model model,  @RequestParam String pass, @RequestParam String name, @RequestParam String surname,@RequestParam String email, @RequestParam String... roles){
        userService.addUser(new User(StringEscapeUtils.escapeHtml4(name),StringEscapeUtils.escapeHtml4(surname), passwordEncoder.encode(StringEscapeUtils.escapeHtml4(pass)),StringEscapeUtils.escapeHtml4(email), roles));
        model.addAttribute("user", StringEscapeUtils.escapeHtml4(name));
        return "userAdded";
    }













  /* @PostMapping("/userAdded")
    public String formUser(Model model, User user, HttpServletRequest request){
        if(userService.findByName(user.getName()).isEmpty()){
            List<String> role= new ArrayList<>();
            role.add("USER");
            user.setRoles(role);
            userService.addUser(user);
            //return userCustomization(model,request,"userAdded");
            return "userAdded";

        }
        else{
            //return userCustomization(model,request,"alreadyExistingUser");
            return "userAdded";
        }
    }


    private String userCustomization(Model model, HttpServletRequest request, String page){
        boolean roleUser=false;
        boolean roleAdmin=false;
        boolean roleRestaurant=false;
        boolean viewer=true;
        if(SecurityContextHolder.getContext().getAuthentication()!=null){
            String username=SecurityContextHolder.getContext().getAuthentication().getName();
            model.addAttribute("username",username);
            if(request.isUserInRole("ROLE_USER")){
                roleUser=true;
                viewer=false;
            }
            else if(request.isUserInRole("ROLE_ADMIN")){
                roleAdmin=true;
                viewer=false;
            }
            else if(request.isUserInRole("ROLE_RESTAURANT")){
                roleRestaurant=true;
                viewer=false;
            }
        }
        model.addAttribute("roleUser",roleUser);
        model.addAttribute("roleAdmin",roleAdmin);
        model.addAttribute("roleRestaurant",roleRestaurant);
        model.addAttribute("viewer",viewer);
        return page;
    }

   */




















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