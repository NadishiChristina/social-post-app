package com.example.post_app_backend.Repository;

import com.example.post_app_backend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**  Represents an interface for connecting with the database using Spring Data JPA
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Find all posts ordered by creation date (newest first)
    List<Post> findAllByOrderByCreatedAtDesc();

}