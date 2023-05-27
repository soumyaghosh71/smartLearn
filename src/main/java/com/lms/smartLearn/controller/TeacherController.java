package com.lms.smartLearn.controller;

import com.lms.smartLearn.exception.ResourceNotFoundException;
import com.lms.smartLearn.model.Teacher;
import com.lms.smartLearn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    // Creates a teacher
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createTeacher(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        return ResponseEntity.ok().build();
    }

    // Deletes a teacher
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable long id) {
        var teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for id" + id));
        teacherRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Fetches a teacher
    @GetMapping("{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for id" + id));
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/")
    public Page<Teacher> getRegistrationPagination(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable p = Pageable.ofSize(pageSize).withPage(page);
        return teacherRepository.findAll(p);
    }
}
