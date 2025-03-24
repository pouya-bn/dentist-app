package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "comments")
public class MongoComment {
    @Id
    private String id;

    @Field("created_date")
    private LocalDateTime createdDate;

    @Field("post_id")
    private Integer postId;

    @Field("user_id")
    private Integer userId;
    private String content;
    private String username;
    private Integer likes = 0;
    private List<Reply> replies = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private List<Attachment> attachments = new ArrayList<>();

    // Nested Reply class
    public static class Reply {
        private String username;
        private String content;

        @Field("reply_id")
        private Integer replyId;

        @Field("created_date")
        private LocalDateTime createdDate;

        @Field("user_id")
        private Integer userId;

        public Reply() {
        }

        public Reply(Integer replyId, String content, LocalDateTime createdDate, Integer userId, String username) {
            this.replyId = replyId;
            this.content = content;
            this.createdDate = createdDate;
            this.userId = userId;
            this.username = username;
        }

        @JsonProperty("reply_id")
        public Integer getReplyId() {
            return replyId;
        }

        public void setReplyId(Integer replyId) {
            this.replyId = replyId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @JsonProperty("created_date")
        public LocalDateTime getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
        }

        @JsonProperty("user_id")
        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    // Nested Attachment class
    public static class Attachment {
        private String type;
        private String description;

        public Attachment() {
        }

        public Attachment(String type, String description) {
            this.type = type;
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public MongoComment() {
    }

    public MongoComment(String content, LocalDateTime createdDate, Integer postId, Integer userId, String username) {
        this.content = content;
        this.createdDate = createdDate;
        this.postId = postId;
        this.userId = userId;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("created_date")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("post_id")
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}