package com.lms.smartLearn.repository;

import com.lms.smartLearn.model.Registration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Optional<Registration> findOneByStudentIdAndCourseId(long studentId, long courseId);

    @Query("from Registration r where r.student.id = :stId and r.course.id = :crId order by r.id desc limit 1 offset 0")
    Optional<Registration> searchByStudentIdAndCourseId(long stId, long crId);
}
