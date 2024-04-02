package com.vivek.dailyKind.repository;

import com.vivek.dailyKind.user.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    public Optional<Post> findByIdAndUserId(Integer id, Integer userId);
}
