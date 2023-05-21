package com.lms.smartLearn.controller;

import com.lms.smartLearn.exception.ResourceNotFoundException;
import com.lms.smartLearn.model.Registration;
import com.lms.smartLearn.repository.CourseRepository;
import com.lms.smartLearn.repository.RegistrationRepository;
import com.lms.smartLearn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Registration> createCourse(@RequestBody Registration registration, @PathVariable long studentId, @PathVariable long courseId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for id" + studentId));
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for id" + courseId));
        registration.setStudent(student);
        registration.setCourse(course);
        registrationRepository.save(registration);
        return ResponseEntity.ok(registration);
    }
}
