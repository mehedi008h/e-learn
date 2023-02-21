package com.elearn.course.mapper;

import com.elearn.course.dto.CourseDTO;
import com.elearn.course.modal.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseMapper {
    private final InstructorMapper instructorMapper;

    public CourseDTO fromCourse(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        courseDTO.setInstructor(instructorMapper.fromInstructor(course.getInstructor()));
        return courseDTO;
    }

    public Course fromCourseDTO(CourseDTO courseDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        return course;
    }
}
