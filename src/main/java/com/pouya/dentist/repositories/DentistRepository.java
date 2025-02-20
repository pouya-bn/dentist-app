package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistRepository extends JpaRepository<Dentist, Integer> {
}
