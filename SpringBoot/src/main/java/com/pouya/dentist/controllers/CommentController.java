package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Comment;
import com.pouya.dentist.models.MongoComment;
import com.pouya.dentist.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Retrieves all comments.
     *
     * @return a list of all comments
     */
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id the ID of the comment
     * @return the comment with the specified ID
     */
    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    /**
     * Creates a new comment.
     *
     * @param comment the comment to create
     * @return the created comment
     */
    @PostMapping("/comments")
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    /**
     * Updates an existing comment.
     *
     * @param id the ID of the comment to update
     * @param comment the updated comment details
     * @return the updated comment
     */
    @PutMapping("/comments/{id}")
    public Comment updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id the ID of the comment to delete
     * @return a message indicating the result of the deletion
     */
    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable Integer id) {
        return commentService.deleteComment(id);
    }

    /**
     * Retrieves all MongoDB comments.
     * @return a list of all MongoDB comments
     */
    @GetMapping("/mongo/comments")
    public List<MongoComment> getAllMongoComments() {
        return commentService.getAllMongoComments();
    }

    /**
     * Retrieves a MongoDB comment by its ID.
     * @param id the ID of the MongoDB comment
     * @return the MongoDB comment with the specified ID
     */
    @GetMapping("/mongo/comments/{id}")
    public MongoComment getMongoCommentById(@PathVariable String id) {
        return commentService.getMongoCommentById(id);
    }

    /**
     * Retrieves all MongoDB comments for a specific post.
     * @param postId the ID of the post
     * @return a list of MongoDB comments for the specified post
     */
    @GetMapping("/mongo/comments/post/{postId}")
    public List<MongoComment> getMongoCommentsByPostId(@PathVariable Integer postId) {
        return commentService.getMongoCommentsByPostId(postId);
    }

    /**
     * Creates a new MongoDB comment.
     * @param comment the MongoDB comment to create
     * @return the created MongoDB comment
     */
    @PostMapping("/mongo/comments")
    public MongoComment createMongoComment(@RequestBody MongoComment comment) {
        return commentService.createMongoComment(comment);
    }

    /**
     * Updates an existing MongoDB comment.
     * @param id the ID of the MongoDB comment to update
     * @param comment the updated MongoDB comment details
     * @return the updated MongoDB comment
     */
    @PutMapping("/mongo/comments/{id}")
    public MongoComment updateMongoComment(@PathVariable String id, @RequestBody MongoComment comment) {
        return commentService.updateMongoComment(id, comment);
    }

    /**
     * Deletes a MongoDB comment by its ID.
     * @param id the ID of the MongoDB comment to delete
     * @return a message indicating the result of the deletion
     */
    @DeleteMapping("/mongo/comments/{id}")
    public String deleteMongoComment(@PathVariable String id) {
        return commentService.deleteMongoComment(id);
    }
}