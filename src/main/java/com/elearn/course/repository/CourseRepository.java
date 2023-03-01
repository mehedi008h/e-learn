package com.elearn.course.repository;

import com.elearn.course.modal.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findCoursesByCourseNameContains(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM courses WHERE course_id IN (SELECT e.course_id FROM enrolled_in AS e WHERE e.student_id=:studentId)", nativeQuery = true)
    Page<Course> getCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "SELECT * FROM courses WHERE course_id NOT IN (SELECT e.course_id FROM enrolled_in AS e WHERE e.student_id=:studentId)", nativeQuery = true)
    Page<Course> getNonEnrolledInCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "SELECT c FROM Course AS c WHERE c.instructor.instructorId=:instructorId")
    Page<Course> getCoursesByInstructorId(@Param("instructorId") Long instructorId, Pageable pageable);

}
