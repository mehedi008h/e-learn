package com.elearn.course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String level;
    private UserDTO user;
}
