package com.cinema.Cinema.restControllers;

import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.services.MovieService;
import com.cinema.Cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRESTController {

    @Autowired
    UserService userService;

   @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUser(id).get();
    }

    @PostMapping("/user/{id}")
    public User getUser2(@PathVariable long id){
        return userService.getUser(id).get();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        if(userService.getUser(id).get().getId() == -1){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @GetMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestParam String name, @RequestParam String surname, @RequestParam String pass, @RequestParam String email){
        userService.addUser(new User(name,surname,pass,email));
    }

    //este metodo de createUser con Post, no funciona
    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser2(@RequestBody User user){
        userService.addUser(user);
    }

    /*
    public void createUser2(@RequestBody String name, @RequestBody String surname, @RequestBody String pass, @RequestBody String email){
        userService.addUser(new User(name,surname,pass,email));
    }
     */

    @GetMapping("/showAllUsers")
    @ResponseStatus(HttpStatus.OK)
    public String showAllUsers(){
        return userService.getUsers().toString();
    }

    @PostMapping("/showAllUsers")
    @ResponseStatus(HttpStatus.OK)
    public String showAllUsers2(){
        return userService.getUsers().toString();
    }

    //este metodo de updateUser (put) no funciona
    @PutMapping("updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userUpdated){
        //User user = new User(name, surname, pass, email);
        if(userService.getUser(id).get().getId() == -1) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            userService.modifyUser(id, userUpdated);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }


}

