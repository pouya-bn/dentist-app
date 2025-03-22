package com.pouya.dentist.repositories;

import com.pouya.dentist.models.MongoComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoCommentRepository extends MongoRepository<MongoComment, String> {
    List<MongoComment> findByPostId(Integer postId);
}