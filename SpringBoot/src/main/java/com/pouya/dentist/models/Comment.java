package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents a Comment entity which is linked to a post and a user.
 * Each comment contains a content, a creation date and metadata about its creation.
 */
@Entity
@Table(name = "comments")
public class Comment {
    /**
     * The unique identifier of the comment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The content of the comment.
     */
    @Column(length = 2000)
    private String content;

    /**
     * The date and time when the comment was created.
     */
    private LocalDateTime createdDate;

    /**
     * The post that this comment belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * The user that created this comment.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The ID of the post that this comment belongs to.
     */
    @Transient
    @JsonProperty("post_id")
    private Integer postId;

    /**
     * The ID of the user that created this comment.
     */
    @Transient
    @JsonProperty("user_id")
    private Integer userId;

    public Comment() {
    }

    /**
     * Creates a new comment with the given content, creation date, post ID and user ID.
     */
    public Comment(String content, LocalDateTime createdDate, Integer postId, Integer userId) {
        this.content = content;
        this.createdDate = createdDate;
        this.postId = postId;
        this.userId = userId;
    }

    /**
     * Gets the unique identifier of the comment.
     * 
     * @return the ID of the comment
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the comment.
     * 
     * @param id the new ID of the comment
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the content of the comment.
     * 
     * @return the content of the comment
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the comment.
     * 
     * @param content the new content of the comment
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the creation date of the comment.
     * 
     * @return the creation date of the comment
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the creation date of the comment.
     * 
     * @param createdDate the new creation date of the comment
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the post that this comment belongs to.
     * 
     * @return the post that this comment belongs to
     */
    public Post getPost() {
        return post;
    }

    /**
     * Sets the post that this comment belongs to.
     * 
     * @param post the new post that this comment belongs to
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Gets the user that created this comment.
     * 
     * @return the user that created this comment
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user that created this comment.
     * 
     * @param user the new user that created this comment
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the ID of the post that this comment belongs to. If the post is not
     * null, it will return the ID of the post, otherwise it will return the
     * transient post ID field.
     * 
     * @return the ID of the post that this comment belongs to
     */
    @JsonProperty("post_id")
    public Integer getPostId() {
        return (post != null) ? post.getId() : postId;
    }

    /**
     * Sets the ID of the post that this comment belongs to.
     * 
     * @param postId the new ID of the post that this comment belongs to
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * Gets the ID of the user that created this comment. If the user is not null,
     * it will return the ID of the user, otherwise it will return the transient
     * user ID field.
     * 
     * @return the ID of the user that created this comment
     */
    @JsonProperty("user_id")
    public Integer getUserId() {
        return (user != null) ? user.getId() : userId;
    }

    /**
     * Sets the ID of the user that created this comment.
     * 
     * @param userId the new ID of the user that created this comment
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}