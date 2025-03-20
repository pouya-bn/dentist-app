package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Post;
import com.pouya.dentist.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
    }

    @Transactional
    public Post createPost(Post post) {
        post.setCreatedDate(LocalDateTime.now());
        return postRepository.save(post);
    }

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