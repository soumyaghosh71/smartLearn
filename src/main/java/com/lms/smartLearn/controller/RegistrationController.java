package com.lms.smartLearn.controller;

import com.lms.smartLearn.exception.ResourceNotFoundException;
import com.lms.smartLearn.model.Course;
import com.lms.smartLearn.model.Registration;
import com.lms.smartLearn.model.dto.RegistrationDto;
import com.lms.smartLearn.repository.CourseRepository;
import com.lms.smartLearn.repository.RegistrationRepository;
import com.lms.smartLearn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RegistrationRepository registrationRepository;

    // Registers a student into a course
    @PostMapping("/create/{studentId}/{courseId}")
    public ResponseEntity<HttpStatus> createRegistration(@RequestBody RegistrationDto registration, @PathVariable long studentId, @PathVariable long courseId) {

        return ResponseEntity.ok().build();
    }

    // Deregister a course
    @DeleteMapping("/delete/{studentId}/{courseId}")
    public ResponseEntity<HttpStatus> deleteRegistration(@PathVariable long studentId, @PathVariable long courseId) {
        var registration = registrationRepository.findByStudentIdAndCourseId(studentId, courseId);
        registration.ifPresent(e -> registrationRepository.delete(e));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public Page<Registration> getRegistrationPagination(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable p = Pageable.ofSize(pageSize).withPage(page);
        return registrationRepository.findAll(p);
    }
}
