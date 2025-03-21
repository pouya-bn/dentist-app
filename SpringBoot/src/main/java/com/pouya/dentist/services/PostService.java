package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Post;
import com.pouya.dentist.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing posts.
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * Retrieves a list of all posts.
     * 
     * @return a list of all posts
     */
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Retrieves a post by its ID.
     * 
     * @param id the ID of the post
     * @return the post with the specified ID
     * @throws ResourceNotFoundException if the post is not found
     */
    public Post getPostById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
    }

    /**
     * Creates a new post with the given details.
     * 
     * @param post the post to create
     * @return the created post
     */
    @Transactional
    public Post createPost(Post post) {
        post.setCreatedDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    /**
     * Updates an existing post with the given details.
     * 
     * @param id the ID of the post to update
     * @param postDetails the new details of the post
     * @return the updated post
     * @throws ResourceNotFoundException if the post is not found
     */
    @Transactional
    public Post updatePost(Integer id, Post postDetails) {
        Post existingPost = getPostById(id);
        existingPost.setTitle(postDetails.getTitle());
        existingPost.setContent(postDetails.getContent());
        existingPost.setBoardId(postDetails.getBoardId());
        existingPost.setUserId(postDetails.getUserId());
        existingPost.setCreatedDate(postDetails.getCreatedDate() != null ?
                postDetails.getCreatedDate() :
                LocalDateTime.now());
        return postRepository.save(existingPost);
    }

    /**
     * Deletes a post by its ID.
     * 
     * @param id the ID of the post to delete
     * @return a message indicating the result of the deletion
     * @throws ResourceNotFoundException if the post is not found
     */
    @Transactional
    public String deletePost(Integer id) {
        try {
            getPostById(id);
            postRepository.deleteById(id);
            return "Post with id " + id + " deleted successfully";
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                return "Post not found with id " + id;
            }
            return "Error deleting post with id " + id;
        }
    }
}