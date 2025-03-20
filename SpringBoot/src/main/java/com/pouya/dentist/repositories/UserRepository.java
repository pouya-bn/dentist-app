package com.pouya.dentist.repositories;

import com.pouya.dentist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends the JpaRepository interface to provide the basic
 * CRUD operations for the User entity.
 * This interface is used by the UserService class to interact with the
 * User entity in the database.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
