package com.lms.smartLearn.controller;

import com.lms.smartLearn.exception.ResourceNotFoundException;
import com.lms.smartLearn.model.Course;
import com.lms.smartLearn.repository.CourseRepository;
import com.lms.smartLearn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    // Creates a course
    @PostMapping("/create/{teacherId}")
    public ResponseEntity<HttpStatus> createCourse(@RequestBody Course course, @PathVariable long teacherId) {
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for id" + teacherId));
        course.setTeacher(teacher);
        courseRepository.save(course);
        return ResponseEntity.ok().build();
    }

    // Fetches a course details
    @GetMapping("{id}")
    public ResponseEntity<Course> geCourseById(@PathVariable long id) {
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for id" + id));
        return ResponseEntity.ok(course);
    }
}
