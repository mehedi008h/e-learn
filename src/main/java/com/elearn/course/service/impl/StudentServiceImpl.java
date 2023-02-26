package com.elearn.course.service.impl;

import com.elearn.course.dto.StudentDTO;
import com.elearn.course.mapper.StudentMapper;
import com.elearn.course.modal.Student;
import com.elearn.course.modal.User;
import com.elearn.course.repository.StudentRepository;
import com.elearn.course.service.StudentService;
import com.elearn.course.service.UserService;
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
public class StudentServiceImpl implements StudentService {
    private final UserService userService;
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    // create student
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        // create user
        User user = userService.createUser(studentDTO.getUser().getEmail(), studentDTO.getUser().getPassword());
        // assign role Student to user
        userService.assignRoleToUser(user.getEmail(), "Student");
        Student student = studentMapper.fromStudentDTO(studentDTO);
        student.setUser(user);
        Student saveStudent = studentRepository.save(student);
        return studentMapper.fromStudent(saveStudent);
    }

    // load student by id
    @Override
    public Student loadStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));
    }

    // load student by email
    @Override
    public StudentDTO loadStudentByEmail(String email) {
        return studentMapper.fromStudent(studentRepository.findStudentByEmail(email));
    }

    // find students by name
    @Override
    public Page<StudentDTO> loadStudentByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findStudentsByName(name, pageRequest);
        return new PageImpl<>(studentPage.getContent()
                .stream().map(student -> studentMapper.fromStudent(student))
                .collect(Collectors.toList()), pageRequest, studentPage.getTotalElements());
    }

}
