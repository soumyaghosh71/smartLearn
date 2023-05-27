package com.lms.smartLearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class RegistrationDto {
    public RegistrationDto(Date date, Long course, Long student) {
        this.date = date;
        this.course = course;
        this.student = student;
    }

    private long id;
    private Date date;
    private Long course;
    private Long student;
}
