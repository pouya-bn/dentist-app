package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents a Post entity which is linked to a Board and a User.
 * Each post contains a title, content, and metadata about its creation.
 */
@Entity
@Table(name = "posts")
public class Post {

    /**
     * The unique identifier of the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The title of the post.
     */
    private String title;

    /**
     * The content of the post, limited to 5000 characters.
     */
    @Column(length = 5000)
    private String content;

    /**
     * The date and time when the post was created.
     */
    private LocalDateTime createdDate;

    /**
     * The board to which this post belongs.
     */
    @ManyToOne
    @JoinColumn(name = "board_id", insertable = false, updatable = false)
    private Board board;

    /**
     * The user who created the post.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    /**
     * The identifier of the board to which this post is linked.
     */
    @Column(name = "board_id")
    @JsonProperty("board_id")
    private Integer boardId;

    /**
     * The identifier of the user who created the post.
     */
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Default constructor.
     */
    public Post() {
    }

    /**
     * Constructs a new Post with the specified title, content, creation date,
     * board ID, and user ID.
     */
    public Post(String title, String content, LocalDateTime createdDate, Integer boardId, Integer userId) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.boardId = boardId;
        this.userId = userId;
    }

    /**
     * Returns the unique identifier of the post.
     *
     * @return the ID of the post
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the post.
     *
     * @param id the new ID of the post
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the title of the post.
     *
     * @return the title of the post
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the post.
     *
     * @param title the new title of the post
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the content of the post.
     *
     * @return the content of the post
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the post.
     *
     * @param content the new content of the post
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the creation date and time of the post.
     *
     * @return the creation date and time of the post
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the creation date and time of the post.
     *
     * @param createdDate the new creation date and time of the post
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Returns the board ID to which this post is linked.
     *
     * @return the board ID
     */
    public Integer getBoardId() {
        return boardId;
    }

    /**
     * Sets the board ID to which this post is linked.
     *
     * @param boardId the new board ID
     */
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /**
     * Returns the user ID who created the post.
     *
     * @return the user ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID who created the post.
     *
     * @param userId the new user ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}