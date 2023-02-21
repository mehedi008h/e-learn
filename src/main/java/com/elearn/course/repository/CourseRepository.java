package com.elearn.course.repository;

import com.elearn.course.modal.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
