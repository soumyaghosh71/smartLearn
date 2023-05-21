package com.lms.smartLearn.repository;

import com.lms.smartLearn.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
