package com.elearn.course.runner;

import com.elearn.course.dto.CourseDTO;
import com.elearn.course.dto.InstructorDTO;
import com.elearn.course.dto.StudentDTO;
import com.elearn.course.dto.UserDTO;
import com.elearn.course.service.*;
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
    private final InstructorService instructorService;
    private final CourseService courseService;

    @Override
    public void run(String... args) throws Exception {
//        createRoles();
//        createAdmin();
//        createInstructors();
//        createCourses();
//        createStudents();
    }

    private void createRoles() {
        Arrays.asList("Admin", "Instructor", "Student").forEach(role -> roleService.createRole(role));
    }

    private void createAdmin() {
        userService.createUser("admin@gmail.com", "12345");
        userService.assignRoleToUser("admin@gmail.com", "Admin");
    }

    private void createInstructors() {
        for (int i = 0; i < 10; i++) {
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setFirstName("instructor" + i + "FN");
            instructorDTO.setLastName("instructor" + i + "LN");
            instructorDTO.setSummary("master" + i);
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail("instructors" + i + "@gmail.com");
            userDTO.setPassword("1234");
            instructorDTO.setUser(userDTO);
            instructorService.createInstructor(instructorDTO);
        }
    }

    private void createCourses() {
        for (int i = 0; i < 20; i++) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setCourseDescription("Java" + i);
            courseDTO.setCourseDuration(i + "Hours");
            courseDTO.setCourseName("Java Course" + i);
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setInstructorId(1L);
            courseDTO.setInstructor(instructorDTO);
            courseService.createCourse(courseDTO);
        }
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

    private void assignCourseToStudent(StudentDTO student) {
        courseService.assignStudentToCourse(1L, student.getStudentId());
    }
}
