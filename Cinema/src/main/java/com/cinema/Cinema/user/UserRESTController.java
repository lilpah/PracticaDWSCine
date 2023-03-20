package com.cinema.Cinema.user;

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

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        if(userService.getUsers() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public void createUser2(@RequestBody String name, @RequestBody String surname, @RequestBody String pass, @RequestBody String email){
        userService.addUser(new User(name,surname,pass,email));
    }


    @GetMapping("/showAllUsers")
    @ResponseStatus(HttpStatus.OK)
    public String showAllUsers(){
        return userService.getUsers().toString();
    }

    @PutMapping("/user/{id}/modifyUser")
    @ResponseStatus(HttpStatus.OK)
    public void showAllUsers(@PathVariable long id, @RequestBody String name, @RequestBody String surname, @RequestBody String pass, @RequestBody String email){
        User user = new User(name, surname, pass, email);
        userService.getUsers().put(id, user);
    }

}
