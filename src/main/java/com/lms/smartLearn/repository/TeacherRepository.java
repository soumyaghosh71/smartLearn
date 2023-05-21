package com.lms.smartLearn.repository;

import com.lms.smartLearn.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
