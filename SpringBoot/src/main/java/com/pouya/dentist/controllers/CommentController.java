package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Comment;
import com.pouya.dentist.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing comments.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Retrieves all comments.
     *
     * @return a list of all comments
     */
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id the ID of the comment
     * @return the comment with the specified ID
     */
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    /**
     * Creates a new comment.
     *
     * @param comment the comment to create
     * @return the created comment
     */
    @PostMapping
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
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id the ID of the comment to delete
     * @return a message indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Integer id) {
        return commentService.deleteComment(id);
    }
}