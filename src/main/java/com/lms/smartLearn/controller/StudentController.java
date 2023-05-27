package com.lms.smartLearn.controller;

import com.lms.smartLearn.exception.ResourceNotFoundException;
import com.lms.smartLearn.model.Student;
import com.lms.smartLearn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Creates a student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student save = studentRepository.save(student);
        return ResponseEntity.ok(save);
    }

    // Deletes a student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for id" + id));
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Fetches a student
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for id" + id));
        return ResponseEntity.ok(student);
    }

    @GetMapping("/")
    public Page<Student> getRegistrationPagination(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable p = Pageable.ofSize(pageSize).withPage(page);
        return studentRepository.findAll(p);
    }
};
