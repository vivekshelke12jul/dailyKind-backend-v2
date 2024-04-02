package com.vivek.dailyKind.dao;

import com.vivek.dailyKind.repository.PostRepository;
import com.vivek.dailyKind.repository.UserRepository;
import com.vivek.dailyKind.user.User;
import com.vivek.dailyKind.user.post.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class PostDAO {

    UserRepository userRepository;
    PostRepository postRepository;

    public PostDAO(PostRepository postRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public ResponseEntity<Post> createPost(Integer id, Post post) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            post.setUser(optionalUser.get());

            Post savedPost = postRepository.save(post);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedPost.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        else{
            throw new RuntimeException("User not found");
        }
    }


    public Post getPostByIdOfUserById(Integer uid, Integer pid) {
        System.out.println(uid + " " + pid);
        Optional<User> optionalUser = userRepository.findById(uid);
        if (optionalUser.isPresent()) {
            System.out.println(postRepository.findById(pid));
            Optional<Post> optionalPost = postRepository.findByIdAndUserId(pid, uid);
            if (optionalPost.isPresent()) {
                return optionalPost.get();
            }
            else{
                throw new RuntimeException("Post not found");
            }
        }
        else{
            throw new RuntimeException("User not found");
        }
    }

    public void deletePostByIdOfUserById(Integer uid, Integer pid) {
        Optional<User> optionalUser = userRepository.findById(uid);
        if (optionalUser.isPresent()) {
            Optional<Post> optionalPost = postRepository.findByIdAndUserId(pid, uid);
            if (optionalPost.isPresent()) {
                postRepository.deleteById(pid);
            }
            else{
                throw new RuntimeException("Post not found");
            }
        }
        else{
            throw new RuntimeException("User not found");
        }
    }

    public ResponseEntity<Post> updatePostByIdOfUserById(Integer uid, Integer pid, Post post) {
        Optional<User> optionalUser = userRepository.findById(uid);
        if (optionalUser.isPresent()) {
            Optional<Post> optionalPost = postRepository.findByIdAndUserId(pid, uid);
            if (optionalPost.isPresent()) {
                postRepository.save(post);
                return ResponseEntity.ok().build();
            }
            else{
                throw new RuntimeException("Post not found");
            }
        }
        else{
            throw new RuntimeException("User not found");
        }
    }
}
