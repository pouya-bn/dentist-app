package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Post;
import com.pouya.dentist.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Integer id, @RequestBody Post post) {
        Post existingPost = postService.getPostById(id);
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setBoardId(post.getBoardId());
        existingPost.setUserId(post.getUserId());
        existingPost.setCreatedDate(post.getCreatedDate() != null ?
                post.getCreatedDate() :
                LocalDateTime.now());
        return postService.updatePost(id, existingPost);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Integer id) {
        try {
            postService.deletePost(id);
            return "Post with id " + id + " deleted successfully";
        } catch (Exception e) {
            return "Error deleting post with id " + id;
        }
    }
}