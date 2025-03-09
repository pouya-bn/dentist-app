package com.pouya.dentist.repositories;

import com.pouya.dentist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
