package com.example.post_app_backend.Service;

import com.example.post_app_backend.Entity.Post;
import com.example.post_app_backend.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Service class that handles business logic for CRUD operations on Post entity,
 * including image upload/save, deletion, and like/dislike functionalities.
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Value("${app.upload.dir:uploads/}")
    private String uploadDir;

    // Get all posts ordered by creation date (newest first)
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // Get post by ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // Create new post
    public Post createPost(String title, String body, MultipartFile imageFile) {
        Post post = new Post(title, body);

        // Handle image upload if provided
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageName = saveImage(imageFile); // Save image
            post.setImage(imageName); // Set image name in Post entity
        }

        return postRepository.save(post); // Save post to database
    }

    // Delete post
    public boolean deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            // Delete associated image if exists
            if (post.getImage() != null) {
                deleteImage(post.getImage());
            }

            postRepository.deleteById(id);
            return true;
        }
        return false; // post not found
    }

    // Like a post
    public Post likePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.incrementLikes(); // Increment likes
            return postRepository.save(post); // Save updated post
        }
        return null;
    }

    // Dislike a post
    public Post dislikePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.incrementDislikes(); // Increment dislikes
            return postRepository.save(post); // Save updated post
        }
        return null;
    }

    // Method to save image file
    private String saveImage(MultipartFile file) {
        try {
            // Create upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            // Save file
            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFilename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image file", e);
        }
    }

    // Method to delete image file
    private void deleteImage(String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            System.err.println("Failed to delete image file: " + filename);
        }
    }
}