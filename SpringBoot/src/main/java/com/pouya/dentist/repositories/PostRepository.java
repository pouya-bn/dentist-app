package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends the JpaRepository interface to provide the basic
 * CRUD operations for the Post entity.
 * This interface is used by the PostService class to interact with the
 * Post entity in the database.
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
}
