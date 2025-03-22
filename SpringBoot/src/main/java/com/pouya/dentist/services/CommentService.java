package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Comment;
import com.pouya.dentist.models.MongoComment;
import com.pouya.dentist.models.Post;
import com.pouya.dentist.models.User;
import com.pouya.dentist.repositories.CommentRepository;
import com.pouya.dentist.repositories.MongoCommentRepository;
import com.pouya.dentist.repositories.PostRepository;
import com.pouya.dentist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoCommentRepository mongoCommentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all comments.
     *
     * @return a list of all comments
     */
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id the ID of the comment
     * @return the comment with the specified ID
     * @throws ResourceNotFoundException if the comment is not found
     */
    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + id));
    }

    /**
     * Creates a new comment with the given details.
     *
     * @param comment the comment to create
     * @return the created comment
     * @throws RuntimeException if any user or post ID is not found
     */
    @Transactional
    public Comment createComment(Comment comment) {
        if (comment.getPostId() != null) {
            Post post = postRepository.findById(comment.getPostId())
                    .orElseThrow(() -> new RuntimeException("Post not found with id: " + comment.getPostId()));
            comment.setPost(post);
        }

        if (comment.getUserId() != null) {
            User user = userRepository.findById(comment.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + comment.getUserId()));
            comment.setUser(user);
        }

        comment.setCreatedDate(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    /**
     * Updates an existing comment with the given details.
     *
     * @param id             the ID of the comment to update
     * @param commentDetails the new details of the comment
     * @return the updated comment
     * @throws RuntimeException if the comment is not found
     */
    @Transactional
    public Comment updateComment(Integer id, Comment commentDetails) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));

        if (commentDetails.getContent() != null) {
            existingComment.setContent(commentDetails.getContent());
        }

        if (commentDetails.getPostId() != null) {
            Post post = postRepository.findById(commentDetails.getPostId())
                    .orElseThrow(() -> new RuntimeException("Post not found with id: " + commentDetails.getPostId()));
            existingComment.setPost(post);
        }

        if (commentDetails.getUserId() != null) {
            User user = userRepository.findById(commentDetails.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + commentDetails.getUserId()));
            existingComment.setUser(user);
        }

        return commentRepository.save(existingComment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id the ID of the comment to delete
     * @return a message indicating the result of the deletion
     */
    public String deleteComment(Integer id) {
        try {
            getCommentById(id);
            commentRepository.deleteById(id);
            return "Comment with id " + id + " deleted successfully";
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                return "Comment not found with id " + id;
            }
            return "Error deleting comment with id " + id;
        }
    }

    /**
     * Retrieves all MongoDB comments.
     *
     * @return a list of all MongoDB comments
     */
    public List<MongoComment> getAllMongoComments() {
        return mongoCommentRepository.findAll();
    }

    /**
     * Retrieves a MongoDB comment by its ID.
     *
     * @param id the ID of the MongoDB comment
     * @return the MongoDB comment with the specified ID
     */
    public MongoComment getMongoCommentById(String id) {
        return mongoCommentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MongoDB Comment not found with id " + id));
    }

    /**
     * Retrieves all MongoDB comments for a specific post.
     *
     * @param postId the ID of the post
     * @return a list of MongoDB comments for the specified post
     */
    public List<MongoComment> getMongoCommentsByPostId(Integer postId) {
        return mongoCommentRepository.findByPostId(postId);
    }

    /**
     * Creates a new MongoDB comment.
     *
     * @param comment the MongoDB comment to create
     * @return the created MongoDB comment
     */
    public MongoComment createMongoComment(MongoComment comment) {
        comment.setCreatedDate(LocalDateTime.now());
        // Tags and attachments are set via the request body, no additional logic needed here
        return mongoCommentRepository.save(comment);
    }

    /**
     * Updates an existing MongoDB comment.
     *
     * @param id             the ID of the MongoDB comment to update
     * @param commentDetails the updated MongoDB comment details
     * @return the updated MongoDB comment
     */
    public MongoComment updateMongoComment(String id, MongoComment commentDetails) {
        MongoComment existingComment = mongoCommentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MongoDB Comment not found with id " + id));
        if (commentDetails.getContent() != null) existingComment.setContent(commentDetails.getContent());
        if (commentDetails.getPostId() != null) existingComment.setPostId(commentDetails.getPostId());
        if (commentDetails.getUserId() != null) existingComment.setUserId(commentDetails.getUserId());
        if (commentDetails.getUsername() != null) existingComment.setUsername(commentDetails.getUsername());
        if (commentDetails.getLikes() != null) existingComment.setLikes(commentDetails.getLikes());
        if (commentDetails.getReplies() != null) existingComment.setReplies(commentDetails.getReplies());
        if (commentDetails.getTags() != null) existingComment.setTags(commentDetails.getTags()); // Handle tags
        if (commentDetails.getAttachments() != null)
            existingComment.setAttachments(commentDetails.getAttachments()); // Handle attachments
        return mongoCommentRepository.save(existingComment);
    }

    /**
     * Deletes a MongoDB comment by its ID.
     *
     * @param id the ID of the MongoDB comment to delete
     * @return a message indicating the result of the deletion
     */
    public String deleteMongoComment(String id) {
        try {
            getMongoCommentById(id);
            mongoCommentRepository.deleteById(id);
            return "MongoDB Comment with id " + id + " deleted successfully";
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                return "MongoDB Comment not found with id " + id;
            }
            return "Error deleting MongoDB comment with id " + id;
        }
    }
}