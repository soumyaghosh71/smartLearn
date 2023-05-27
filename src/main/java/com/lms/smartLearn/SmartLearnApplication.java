package com.lms.smartLearn;

import com.lms.smartLearn.model.Course;
import com.lms.smartLearn.model.Teacher;
import com.lms.smartLearn.repository.CourseRepository;
import com.lms.smartLearn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class SmartLearnApplication implements CommandLineRunner {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public static void main(String[] args) {
        SpringApplication.run(SmartLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && Objects.equals(args[0], "init")) {
            Teacher t = new Teacher();
            t.setEmail("abc@gmail.com");
            t.setFirstName("Soumya");
            t.setLastName("Ghosh");
            Teacher save = teacherRepository.save(t);

            Course c = new Course();
            c.setName("Falana");
            c.setTeacher(save);
            courseRepository.save(c);
        }
    }
}
