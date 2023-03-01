package com.elearn.course.service.impl;

import com.elearn.course.dto.CourseDTO;
import com.elearn.course.mapper.CourseMapper;
import com.elearn.course.modal.Course;
import com.elearn.course.modal.Instructor;
import com.elearn.course.modal.Student;
import com.elearn.course.repository.CourseRepository;
import com.elearn.course.repository.InstructorRepository;
import com.elearn.course.repository.StudentRepository;
import com.elearn.course.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final CourseMapper courseMapper;

    @Override
    public Course loadCourseById(Long courseId) {
        return null;
    }

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

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Course loadedCourse = loadCourseById(courseDTO.getCourseId());
        Instructor instructor = instructorRepository.findById(courseDTO.getInstructor().getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + courseDTO.getInstructor().getInstructorId() + " Not Found"));
        Course course = courseMapper.fromCourseDTO(courseDTO);
        course.setInstructor(instructor);
        course.setStudents(loadedCourse.getStudents());
        Course updateCourse = courseRepository.save(course);
        return courseMapper.fromCourse(updateCourse);
    }

    @Override
    public Page<CourseDTO> findCoursesByCourseName(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> coursePage = courseRepository.findCoursesByCourseNameContains(keyword, pageRequest);
        return new PageImpl<>(coursePage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, coursePage.getTotalElements());
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " Not Found"));
        Course course = loadCourseById(courseId);
        course.assignStudentToCourse(student);
    }

    @Override
    public Page<CourseDTO> fetchCoursesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> studentCoursePage = courseRepository.getCoursesByStudentId(studentId, pageRequest);
        return new PageImpl<>(studentCoursePage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, studentCoursePage.getTotalElements());
    }

    @Override
    public Page<CourseDTO> fetchNonEnrolledInCoursesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> nonEnrolledCoursesPage = courseRepository.getNonEnrolledInCoursesByStudentId(studentId, pageRequest);
        return new PageImpl<>(nonEnrolledCoursesPage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()), pageRequest, nonEnrolledCoursesPage.getTotalElements());
    }

    @Override
    public void removeCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> instructorCoursePage = courseRepository.getCoursesByInstructorId(instructorId, pageRequest);
        return new PageImpl<>(instructorCoursePage.getContent()
                .stream()
                .map(course -> courseMapper.fromCourse(course))
                .collect(Collectors.toList()), pageRequest, instructorCoursePage.getTotalElements());
    }
}
