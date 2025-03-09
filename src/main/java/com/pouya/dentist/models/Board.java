package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "boards_users",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "boards_posts",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts = new ArrayList<>();

    @Transient
    @JsonProperty("user_ids")
    private List<Integer> userIds;

    @Transient
    @JsonProperty("post_ids")
    private List<Integer> postIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

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

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

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

    public void setPostIds(List<Integer> postIds) {
        this.postIds = postIds;
    }

    @JsonIgnore
    public List<Integer> getInputUserIds() {
        return userIds;
    }

    @JsonIgnore
    public List<Integer> getInputPostIds() {
        return postIds;
    }
}