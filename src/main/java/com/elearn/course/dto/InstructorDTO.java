package com.elearn.course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorDTO {
    private Long instructorId;
    private String firstName;
    private String lastName;
    private String summary;
    private UserDTO user;
}
