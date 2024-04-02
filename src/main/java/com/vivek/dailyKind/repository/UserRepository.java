package com.vivek.dailyKind.repository;

import com.vivek.dailyKind.user.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vivek.dailyKind.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
