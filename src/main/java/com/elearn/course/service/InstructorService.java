package com.elearn.course.service;

import com.elearn.course.dto.InstructorDTO;
import com.elearn.course.modal.Instructor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InstructorService {
    Instructor loadInstructorById(Long instructorId);
    Page<InstructorDTO> findInstructorByName(String name, int page, int size);
    InstructorDTO loadInstructorByEmail(String email);
    InstructorDTO createInstructor(InstructorDTO instructorDTO);
    InstructorDTO updateInstructor(InstructorDTO instructorDTO);
    List<InstructorDTO> fetchInstructors();
    void removeInstructor(Long instructorId);
}
