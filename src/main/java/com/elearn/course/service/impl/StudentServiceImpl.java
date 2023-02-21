package com.elearn.course.service.impl;

import com.elearn.course.dto.StudentDTO;
import com.elearn.course.mapper.StudentMapper;
import com.elearn.course.modal.Student;
import com.elearn.course.modal.User;
import com.elearn.course.repository.StudentRepository;
import com.elearn.course.service.StudentService;
import com.elearn.course.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final UserService userService;
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    // create student
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        System.out.println("Student : "+studentDTO);
        // create user
        User user = userService.createUser(studentDTO.getUser().getEmail(), studentDTO.getUser().getPassword());
        // assign role Student to user
        userService.assignRoleToUser(user.getEmail(), "Student");
        Student student = studentMapper.fromStudentDTO(studentDTO);
        student.setUser(user);
        Student saveStudent = studentRepository.save(student);
        return studentMapper.fromStudent(saveStudent);
    }


}
