package com.lms.smartLearn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private List<Registration> registrationList;
}
