package com.pouya.dentist.repositories;

import com.pouya.dentist.models.PatientCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientCaseRepository extends JpaRepository<PatientCase, Integer> {
}
