package com.lms.smartLearn.controller;

import com.lms.smartLearn.model.Course;
import com.lms.smartLearn.model.Registration;
import com.lms.smartLearn.model.Student;
import com.lms.smartLearn.model.dto.RegistrationDto;
import com.lms.smartLearn.repository.CourseRepository;
import com.lms.smartLearn.repository.RegistrationRepository;
import com.lms.smartLearn.repository.StudentRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.info.Info;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@Tag(name = "Registration", description = "")
public class RegistrationController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RegistrationRepository registrationRepository;

    // Registers a student into a course
    @PostMapping("/create")
    public ResponseEntity<Registration> createRegistration(@RequestBody @Valid RegistrationDto registration) {
        Registration regis = new Registration();
        Course c = new Course();
        c.setId(registration.getCourse());
        regis.setCourse(c);
        Student s = new Student();
        s.setId(registration.getStudent());
        regis.setStudent(s);
        regis.setDate(registration.getDate());
        Registration save = registrationRepository.save(regis);
        return ResponseEntity.ok(save);
    }

    // Deregister a course
    @DeleteMapping("/delete/{studentId}/{courseId}")
    public ResponseEntity<Object> deleteRegistration(@PathVariable long studentId, @PathVariable long courseId) {
        var registration = registrationRepository.searchByStudentIdAndCourseId(studentId, courseId);
        return registration.map(e -> {
            registrationRepository.delete(e);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public Page<Registration> getRegistrationPagination(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable p = Pageable.ofSize(pageSize).withPage(page);
        return registrationRepository.findAll(p);
    }
}
