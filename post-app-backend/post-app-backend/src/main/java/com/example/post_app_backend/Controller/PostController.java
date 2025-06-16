package com.example.post_app_backend.Controller;

import com.example.post_app_backend.Entity.Post;
import com.example.post_app_backend.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** REST controller that handles HTTP requests for CRUD operations on posts
 */
@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class PostController {

    @Autowired
    private PostService postService;

    // Get all posts
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            Map<String, Object> response = new HashMap<>();
            response.put("data", posts);
            response.put("message", "Posts retrieved successfully");
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to retrieve posts");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Get single post by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPostById(@PathVariable Long id) {
        try {
            Optional<Post> post = postService.getPostById(id);
            Map<String, Object> response = new HashMap<>();

            if (post.isPresent()) {
                response.put("data", post.get());
                response.put("message", "Post retrieved successfully");
                response.put("success", true);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Post not found");
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to retrieve post");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Create a new post
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> createPost(
            @RequestParam("title") String title,
            @RequestParam("body") String body,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        try {
            // Validate required tile field
            if (title == null || title.trim().isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("message", "Title is required");
                errorResponse.put("success", false);
                return ResponseEntity.badRequest().body(errorResponse);
            }

            // Validate required body field
            if (body == null || body.trim().isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("message", "Body is required");
                errorResponse.put("success", false);
                return ResponseEntity.badRequest().body(errorResponse);
            }

            // Call service to create the post
            Post createdPost = postService.createPost(title.trim(), body.trim(), imageFile);

            Map<String, Object> response = new HashMap<>();
            response.put("data", createdPost);
            response.put("message", "Post created successfully");
            response.put("success", true);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to create post");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Delete a post by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Long id) {
        try {
            boolean deleted = postService.deletePost(id); // Deleting post
            Map<String, Object> response = new HashMap<>();

            if (deleted) {
                response.put("message", "Post deleted successfully");
                response.put("success", true);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Post not found");
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to delete post");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Like post
    @PatchMapping("/{id}/like")
    public ResponseEntity<Map<String, Object>> likePost(@PathVariable Long id) {
        try {
            Post likedPost = postService.likePost(id); // Increment like count
            Map<String, Object> response = new HashMap<>();

            if (likedPost != null) {
                response.put("data", likedPost);
                response.put("message", "Post liked successfully");
                response.put("success", true);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Post not found");
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to like post");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Dislike post
    @PatchMapping("/{id}/dislike")
    public ResponseEntity<Map<String, Object>> dislikePost(@PathVariable Long id) {
        try {
            Post dislikedPost = postService.dislikePost(id); // Increment dislike count
            Map<String, Object> response = new HashMap<>();

            if (dislikedPost != null) {
                response.put("data", dislikedPost);
                response.put("message", "Post disliked successfully");
                response.put("success", true);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Post not found");
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to dislike post");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
