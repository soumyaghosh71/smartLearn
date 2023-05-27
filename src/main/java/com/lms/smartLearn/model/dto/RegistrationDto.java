package com.lms.smartLearn.model.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class RegistrationDto {
    public RegistrationDto() {
        date = new Date();
    }

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
