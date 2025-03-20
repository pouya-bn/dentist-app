package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends the JpaRepository interface to provide the basic
 * CRUD operations for the Comment entity.
 * This interface is used by the CommentService class to interact with the
 * Comment entity in the database.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
