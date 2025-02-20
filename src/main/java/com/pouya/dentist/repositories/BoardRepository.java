package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
