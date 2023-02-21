package com.elearn.course.controller;

import com.elearn.course.dto.CourseDTO;
import com.elearn.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/course")
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class CourseController {

    private final CourseService courseService;

    // create course
    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.createCourse(courseDTO);
    }
}
