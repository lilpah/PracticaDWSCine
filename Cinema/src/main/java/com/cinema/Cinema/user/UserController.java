package com.cinema.Cinema.user;

import com.cinema.Cinema.movie.Movie;
import com.cinema.Cinema.movie.MovieService;
import com.cinema.Cinema.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/showUsers")
    public String showUser(Model model){
        Collection<User> temp = userService.getUsers().values();
        model.addAttribute("users",temp);
        return "usersTable";
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable Long id){
        model.addAttribute("name",userService.getUsers().get(id).getName());
        return "userIndex";
    }

    @GetMapping("/deleteUsers")
    public String deleteUsers(Model model){
        Collection<User> temp = userService.getUsers().values();
        model.addAttribute("users",temp);
        return "deleteUsers";
    }

    @GetMapping("/deleteUsers/{id}")
    public String deleteUser(Model model, @PathVariable Long id){
        model.addAttribute("user",userService.getUsers().get(id).getName());
        userService.deleteUser(id);
        return "userDeleted";
    }
    @GetMapping("/addUsers")
    public String addUser(Model model){
        Collection<User> temp = userService.getUsers().values();
        model.addAttribute("users",temp);
        return "addUsers";
    }

    @GetMapping("/userAdded")
    public String formUser(Model model,  @RequestParam String pass, @RequestParam String name, @RequestParam String surname,@RequestParam String email){
        userService.addUser(new User(name,surname,pass,email));
        model.addAttribute("user",name);
        return "userAdded";
    }

    @GetMapping("/user/{id}/modifyUser")
    public String modifyUser(Model model, @PathVariable Long id){
        model.addAttribute("name",userService.getUsers().get(id).getName());
        model.addAttribute("surname",userService.getUsers().get(id).getSurname());
        model.addAttribute("pass",userService.getUsers().get(id).getPass());
        model.addAttribute("email",userService.getUsers().get(id).getEmail());
        return "modifyUser";
    }

    @GetMapping("/user/{id}/userModified")
    public String userModified(Model model,  @RequestParam String pass, @RequestParam String name, @RequestParam String surname,@PathVariable Long id){
        userService.getUsers().get(id).setName(name);
        userService.getUsers().get(id).setSurname(surname);
        userService.getUsers().get(id).setPass(pass);
        model.addAttribute("name",userService.getUsers().get(id).getName());
        return "userModified";
    }
}
