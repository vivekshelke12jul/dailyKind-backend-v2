package com.vivek.dailyKind.user.post;

import com.vivek.dailyKind.dao.PostDAO;
import com.vivek.dailyKind.dao.UserDAO;
import com.vivek.dailyKind.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class postController {


    UserDAO userdao;
    PostDAO postdao;

    public postController(UserDAO userdao, PostDAO postdao) {
        this.postdao = postdao;
        this.userdao = userdao;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getAllPostsOfUser(@PathVariable Integer id){
        return userdao.getAllPostsOfUser(id);
    }

    @GetMapping("/users/{uid}/posts/{pid}")
    public Post getPostByIdOfUserById(@PathVariable Integer uid, @PathVariable Integer pid){
        return postdao.getPostByIdOfUserById(uid, pid);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Integer id, @Valid @RequestBody Post post){
        return postdao.createPost(id, post);
    }

    @DeleteMapping("/users/{uid}/posts/{pid}")
    public void deleteUser(@PathVariable Integer uid, @PathVariable Integer pid){
        postdao.deletePostByIdOfUserById(uid, pid);
    }

    @PutMapping("/users/{uid}/posts/{pid}")
    public ResponseEntity<Post> updateUser(@PathVariable Integer uid, @PathVariable Integer pid, @RequestBody Post post){
        return postdao.updatePostByIdOfUserById(uid, pid, post);
    }


}
