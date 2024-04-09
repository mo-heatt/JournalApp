package com.example.journalApp.controller;

import com.example.journalApp.Service.UserService;
import com.example.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @GetMapping("id/{myId}")
    public Optional<User> getUserById(@PathVariable ObjectId myId){
        return userService.findById(myId);
    }

    @DeleteMapping("id/{myId}")
    public void deleteUserById(@PathVariable ObjectId myId){
        userService.deleteById(myId);
    }

    @PutMapping("/{username}") //now we can also change the username
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username){
        User userInDb = userService.findByUsername(username);
        if(userInDb != null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
