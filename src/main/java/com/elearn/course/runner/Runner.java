package com.elearn.course.runner;

import com.elearn.course.dto.StudentDTO;
import com.elearn.course.dto.UserDTO;
import com.elearn.course.service.RoleService;
import com.elearn.course.service.StudentService;
import com.elearn.course.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final RoleService roleService;
    private final UserService userService;
    private final StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
//        createRoles();
//        createAdmin();
        createStudents();
    }

    private void createRoles() {
        Arrays.asList("Admin", "Instructor", "Student").forEach(role -> roleService.createRole(role));
    }

    private void createAdmin() {
        userService.createUser("admin@gmail.com", "12345");
        userService.assignRoleToUser("admin@gmail.com", "Admin");
    }

    private void createStudents() {
        for (int i = 1; i < 10; i++) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName("studentFN" + i);
            studentDTO.setLastName("studentLN" + i);
            studentDTO.setLevel("intermediate" + i);
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail("student" + i + "@gmail.com");
            userDTO.setPassword("1234");
            studentDTO.setUser(userDTO);
            studentService.createStudent(studentDTO);
        }
    }
}
