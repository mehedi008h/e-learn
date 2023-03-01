package com.elearn.course.service;

import com.elearn.course.dto.CourseDTO;
import com.elearn.course.modal.Course;
import org.springframework.data.domain.Page;

public interface CourseService {
    Course loadCourseById(Long courseId);

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(CourseDTO courseDTO);

    Page<CourseDTO> findCoursesByCourseName(String keyword, int page, int size);

    void assignStudentToCourse(Long courseId, Long studentId);

    Page<CourseDTO> fetchCoursesForStudent(Long studentId,int page, int size);

    Page<CourseDTO> fetchNonEnrolledInCoursesForStudent(Long studentId, int page, int size);

    void removeCourse(Long courseId);

    Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size);
}
