package com.cinema.Cinema.restControllers;

import com.cinema.Cinema.entities.User;
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
        return userService.getUsers().get(id);
    }

    @PostMapping("/user/{id}")
    public User getUser2(@PathVariable long id){
        return userService.getUsers().get(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        if(userService.getUsers().get(id).getId() == -1){
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


    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser2(@RequestBody User user){
        userService.addUser(user);
    }

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

    @PutMapping("updateUser/{id}")
    public ResponseEntity<User> showAllUsers(@PathVariable long id, @RequestBody User updateUser){
      //  User user = new User(name, surname, pass, email);
        if(userService.getUsers().get(id).getId() != -1) {
            return new ResponseEntity<>(userService.getUsers().put(id, updateUser), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
