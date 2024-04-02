package com.vivek.dailyKind.dao;

import com.vivek.dailyKind.repository.UserRepository;
import com.vivek.dailyKind.user.User;
import com.vivek.dailyKind.user.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class UserDAO {


    UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }else{
            throw new RuntimeException("User not found");
        }
    }

    public ResponseEntity<User> createUser(User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<User> updateUser(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        }else{
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


    // #################
    //the following are the methods for post's made by user
    // #################


    public List<Post> getAllPostsOfUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get().getPosts();
        }else{
            throw new RuntimeException("User not found");
        }
    }


}
