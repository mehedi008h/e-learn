package com.elearn.course.service;

import com.elearn.course.dto.StudentDTO;
import com.elearn.course.modal.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    // create student
    StudentDTO createStudent(StudentDTO studentDTO);
    // load student by student id
    Student loadStudentById(Long studentId);
    // load student by student email
    StudentDTO loadStudentByEmail(String email);
    // find students by name
    Page<StudentDTO> loadStudentByName(String name, int page, int size);
    // update student
    StudentDTO updateStudent(StudentDTO studentDTO);
    // remove student
    void removeStudent(Long studentId);
}
