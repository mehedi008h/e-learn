package com.elearn.course.service.impl;

import com.elearn.course.dto.CourseDTO;
import com.elearn.course.mapper.CourseMapper;
import com.elearn.course.modal.Course;
import com.elearn.course.modal.Instructor;
import com.elearn.course.repository.CourseRepository;
import com.elearn.course.repository.InstructorRepository;
import com.elearn.course.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CourseMapper courseMapper;

    // create course
    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.fromCourseDTO(courseDTO);
        // find instructor
        Instructor instructor = instructorRepository.findById(course.getInstructor().getInstructorId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Instructor with ID " + courseDTO.getInstructor().getInstructorId() + " not found!"));
        course.setInstructor(instructor);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.fromCourse(savedCourse);
    }
}
