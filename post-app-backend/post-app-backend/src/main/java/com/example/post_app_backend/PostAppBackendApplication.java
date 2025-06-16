package com.example.post_app_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Main Running Application
 */
@SpringBootApplication
public class PostAppBackendApplication {

	// Helpful logs in console on startup
	public static void main(String[] args) {
		SpringApplication.run(PostAppBackendApplication.class, args);
		System.out.println("Post App Backend is running on http://localhost:8080");
		System.out.println("API endpoints available:");
		System.out.println("  GET    /posts           - Get all posts");
		System.out.println("  POST   /posts/add       - Create new post");
		System.out.println("  GET    /posts/{id}      - Get post by ID");
		System.out.println("  DELETE /posts/{id}      - Delete post");
		System.out.println("  PATCH  /posts/{id}/like - Like post");
		System.out.println("  PATCH  /posts/{id}/dislike - Dislike post");
		System.out.println("  GET    /uploads/{filename} - Serve uploaded images");
	}

}
