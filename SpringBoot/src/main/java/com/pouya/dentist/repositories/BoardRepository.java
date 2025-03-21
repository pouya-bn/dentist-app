package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends the JpaRepository interface to provide the basic
 * CRUD operations for the Board entity.
 * This interface is used by the BoardService class to interact with the
 * Board entity in the database.
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
