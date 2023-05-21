package com.lms.smartLearn.repository;

import com.lms.smartLearn.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
