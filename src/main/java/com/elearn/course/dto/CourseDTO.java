package com.elearn.course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private Long courseId;
    private String courseName;
    private String courseDuration;
    private String courseDescription;
    private InstructorDTO instructor;
}
