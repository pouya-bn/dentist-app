package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Post;
import com.pouya.dentist.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing posts.
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Retrieves all posts.
     *
     * @return a list of all posts
     */
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id the ID of the post
     * @return the post with the specified ID
     */
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    /**
     * Creates a new post.
     *
     * @param post the post to create
     * @return the created post
     */
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    /**
     * Updates an existing post.
     *
     * @param id the ID of the post to update
     * @param post the updated post details
     * @return the updated post
     */
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Integer id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    /**
     * Deletes a post by its ID.
     *
     * @param id the ID of the post to delete
     * @return a message indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Integer id) {
        return postService.deletePost(id);
    }
}