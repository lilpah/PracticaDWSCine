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
    @GetMapping("/user/{id}/showTickets")
    public String showTickets(Model model, @PathVariable Long id){
        User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets",temp);
        return "ticketsUser";
    }

   @GetMapping("/user/{id}/buyTickets")
    public String buyTickets(Model model, @PathVariable Long id){
        User user = userService.getUsers().get(id);
        model.addAttribute("movies",userService.getMovies().values());
        model.addAttribute("name",user.getName());
        return "tickets";
    }


   @GetMapping("/user/{id}/formTicket")
    public String formTicket(Model model, @RequestParam long idMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable Long id){
        Ticket tmp = new Ticket(idMovie, numSeat, movieTime, movieDate);
        userService.addTicket(id, tmp);
        return "ticketBookedCorrectly";
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable Long id){
        model.addAttribute("name",userService.getUsers().get(id).getName());
        return "userIndex";
    }
    @GetMapping("/user/{id}/deleteTicket")
    public String deleteTicket(Model model, @PathVariable Long id){
        User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets",temp);
        return "deleteATicket";
    }

    @GetMapping("/user/{id}/deleteTicket/{idTicket}")
    public String formDeleteTicket(@PathVariable long idTicket, @PathVariable long id){
        userService.deleteTicket(id,idTicket);
        return "ticketDeletedCorrectly";
    }


    @GetMapping("/user/{id}/modifyTicket")
    public String modifyTickets(Model model, @PathVariable Long id){
        User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets",temp);
        return "modifyTickets";
    }
    @GetMapping("/user/{id}/modifyTicket/{idTicket}")
    public String modifyTicket(Model model,@PathVariable long idTicket,@PathVariable long id){
        model.addAttribute("numSeat",userService.getUsers().get(id).getTickets().get(idTicket).getNumSeat());
        model.addAttribute("idMovie",userService.getUsers().get(id).getTickets().get(idTicket).getIdMovie());
        model.addAttribute("nameMovie",userService.getMovies().get(userService.getUsers().get(id).getTickets().get(idTicket).getIdMovie()).getName());
        model.addAttribute("movieTime",userService.getUsers().get(id).getTickets().get(idTicket).getMovieTime());
        model.addAttribute("movieDate",userService.getUsers().get(id).getTickets().get(idTicket).getMovieDate());
        return "modifyATicket";
    }
    @GetMapping("/user/{id}/formTicket/{idTicket}")
    public String formTicket(@RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable Long id,@PathVariable Long idTicket){
        userService.getUsers().get(id).getTickets().get(idTicket).setNumSeat(numSeat);
        userService.getUsers().get(id).getTickets().get(idTicket).setMovieTime(movieTime);
        userService.getUsers().get(id).getTickets().get(idTicket).setMovieDate(movieDate);
        return "ticketsModified";
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
