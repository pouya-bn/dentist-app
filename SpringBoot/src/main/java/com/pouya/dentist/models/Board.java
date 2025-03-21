package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Board entity which is a community of users that can create and read posts.
 * A board has a name, description, and a list of users that are members of the board.
 * Each board also has a list of posts that are created by its members.
 */
@Entity
@Table(name = "boards")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Board {
    /**
     * Unique identifier of the board.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of the board.
     */
    private String name;

    /**
     * Description of the board.
     */
    private String description;

    /**
     * List of users that are members of the board.
     */
    @ManyToMany
    @JoinTable(
            name = "boards_users",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    /**
     * List of posts that are created by the members of the board.
     */
    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

    /**
     * List that holds the IDs of the users that are members of the board.
     */
    @Transient
    @JsonProperty("user_ids")
    private List<Integer> userIds;

    /**
     * Listthat holds the IDs of the posts that are created by the members of the board.
     */
    @Transient
    @JsonProperty("post_ids")
    private List<Integer> postIds;

    /**
     * Default constructor.
     */
    public Board() {
    }

    /**
     * Constructor that takes the name and description of the board.
     */
    public Board(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Getter for the ID of the board.
     *
     * @return the ID of the board
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID of the board.
     *
     * @param id the new ID of the board
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for the name of the board.
     *
     * @return the name of the board
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the board.
     *
     * @param name the new name of the board
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the description of the board.
     *
     * @return the description of the board
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the board.
     *
     * @param description the new description of the board
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the list of users that are members of the board.
     *
     * @return the list of users that are members of the board
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Setter for the list of users that are members of the board.
     *
     * @param users the new list of users that are members of the board
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Getter for the list of posts that are created by the members of the board.
     *
     * @return the list of posts that are created by the members of the board
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Setter for the list of posts that are created by the members of the board.
     *
     * @param posts the new list of posts that are created by the members of the board
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    /**
     * Getter for the IDs of the users that are members of the board.
     *
     * @return the IDs of the users that are members of the board
     */
    @JsonProperty("user_ids")
    public List<Integer> getUserIds() {
        List<Integer> ids = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                ids.add(user.getId());
            }
        }
        return ids;
    }

    /**
     * Setter for the IDs of the users that are members of the board.
     *
     * @param userIds the new IDs of the users that are members of the board
     */
    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    /**
     * Getter for the IDs of the posts that are created by the members of the board.
     *
     * @return the IDs of the posts that are created by the members of the board
     */
    @JsonProperty("post_ids")
    public List<Integer> getPostIds() {
        List<Integer> ids = new ArrayList<>();
        if (posts != null) {
            for (Post post : posts) {
                ids.add(post.getId());
            }
        }
        return ids;
    }

    /**
     * Setter for the IDs of the posts that are created by the members of the board.
     *
     * @param postIds the new IDs of the posts that are created by the members of the board
     */
    public void setPostIds(List<Integer> postIds) {
        this.postIds = postIds;
    }

    /**
     * Getter for the IDs of the users that are members of the board.
     *
     * @return the IDs of the users that are members of the board
     */
    @JsonIgnore
    public List<Integer> getInputUserIds() {
        return userIds;
    }

    /**
     * Getter for the IDs of the posts that are created by the members of the board.
     *
     * @return the IDs of the posts that are created by the members of the board
     */
    @JsonIgnore
    public List<Integer> getInputPostIds() {
        return postIds;
    }
}