package com.elearn.course.repository;

import com.elearn.course.modal.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // query for find student by email
    @Query(value = "SELECT s FROM Student AS s WHERE s.user.email=:email")
    Student findStudentByEmail(@Param("email") String email);

    // query for find students by name
    @Query(value = "SELECT s FROM Student AS s where s.firstName LIKE %:name% OR s.lastName LIKE %:name%")
    Page<Student> findStudentsByName(@Param("name") String name, PageRequest pageRequest);

}
