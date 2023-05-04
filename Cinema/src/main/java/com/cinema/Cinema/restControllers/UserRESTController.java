package com.cinema.Cinema.restControllers;

import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserRESTController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @JsonView(User.UserView.class)
    @GetMapping("/user")
    public ResponseEntity<User> getUser(HttpServletRequest request){
       if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
           String name = request.getUserPrincipal().getName();
           User user = userService.findByName(name).orElseThrow();
           return new ResponseEntity<>(userService.getUser(user.getId()), HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }

    }

    @JsonView(User.UserView.class)
    @PostMapping("/user")
    public ResponseEntity<User> getUser2(HttpServletRequest request){
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            String name = request.getUserPrincipal().getName();
            User user = userService.findByName(name).orElseThrow();
            return new ResponseEntity<>(userService.getUser(user.getId()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/admin/deleteAnyUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        if(userService.getUser(id).getId() == -1){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @DeleteMapping("/deleteMyUser")
    public ResponseEntity<User> deleteMyUser(HttpServletRequest request) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            String name = request.getUserPrincipal().getName();
            User user = userService.findByName(name).orElseThrow();
            userService.deleteUser(user.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestParam String name, @RequestParam String surname, @RequestParam String pass, @RequestParam String email){
        userService.addUser(new User(name,surname,passwordEncoder.encode(pass),email, "USER"));
    }

    //este metodo de createUser con Post, no funciona
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser2(@RequestBody User user){
        List<String> temp = new ArrayList<>();
        temp.add("USER");
        user.setRoles(temp);


        String passTemp = user.getPass();
        user.setPass(passwordEncoder.encode(passTemp));
        userService.addUser(user);
    }

    /*
    public void createUser2(@RequestBody String name, @RequestBody String surname, @RequestBody String pass, @RequestBody String email){
        userService.addUser(new User(name,surname,pass,email));
    }
     */

    @JsonView(User.UserViewAdmin.class)
    @GetMapping("/admin/showAllUsers")
    @ResponseStatus(HttpStatus.OK)
    public Collection<User> showAllUsers(){
        return userService.getUsers();
    }


    @JsonView(User.UserViewAdmin.class)
    @PostMapping("/admin/showAllUsers")
    @ResponseStatus(HttpStatus.OK)
    public Collection<User> showAllUsers2(){
        return userService.getUsers();
    }

    //este metodo de updateUser (put) no funciona
    @PutMapping("updateUser")
    public ResponseEntity<User> getUser(HttpServletRequest request, @RequestBody User userUpdated){
        //User user = new User(name, surname, pass, email);

        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            String name = request.getUserPrincipal().getName();
            User user = userService.findByName(name).orElseThrow();
            userUpdated.setPass(passwordEncoder.encode(userUpdated.getPass()));
            userService.modifyUser(user.getId(), userUpdated);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }




}

