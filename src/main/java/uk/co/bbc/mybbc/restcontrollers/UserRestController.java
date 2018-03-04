package uk.co.bbc.mybbc.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.bbc.mybbc.entities.NotificationResponse;
import uk.co.bbc.mybbc.entities.User;
import uk.co.bbc.mybbc.services.NotificationService;
import uk.co.bbc.mybbc.services.UserService;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user", produces="application/json")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User registerUser(@Valid @RequestBody User user){
        return userService.register(user);
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<User> listUsers(){
        return userService.getAll();
    }


    @ResponseBody
    @RequestMapping(value = "notify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public NotificationResponse sendNotification(@RequestBody Map<String, String> jsonRequest){
        return notificationService.sendNotification(jsonRequest);
    }

}
