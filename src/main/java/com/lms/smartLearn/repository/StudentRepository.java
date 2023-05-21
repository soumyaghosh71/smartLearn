package com.lms.smartLearn.repository;

import com.lms.smartLearn.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
