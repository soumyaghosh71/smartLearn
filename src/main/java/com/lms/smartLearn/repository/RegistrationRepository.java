package com.lms.smartLearn.repository;

import com.lms.smartLearn.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    @Query("from Registration where student_id = :student_id and course_id = :course_id")
    Registration findByStudentIdAndCourseId(long student_id, long course_id);
}
