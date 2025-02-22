package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Post;
import com.pouya.dentist.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Post createPost(Post post) {
        post.setCreatedDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Post updatePost(Integer id, Post postDetails) {
        Post post = getPostById(id);
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setBoardId(postDetails.getBoardId());
        post.setUserId(postDetails.getUserId());
        return postRepository.save(post);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}