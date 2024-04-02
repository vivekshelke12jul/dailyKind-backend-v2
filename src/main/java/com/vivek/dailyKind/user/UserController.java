package com.vivek.dailyKind.user;


import com.vivek.dailyKind.dao.UserDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    UserDAO userdao;

    public UserController(UserDAO userdao) {
        this.userdao = userdao;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userdao.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id){
        return userdao.getUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        return userdao.createUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userdao.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){ return userdao.updateUser(id, user); }

}
