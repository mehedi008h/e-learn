package com.elearn.course.service.impl;

import com.elearn.course.dto.InstructorDTO;
import com.elearn.course.mapper.InstructorMapper;
import com.elearn.course.modal.Instructor;
import com.elearn.course.modal.User;
import com.elearn.course.repository.InstructorRepository;
import com.elearn.course.service.CourseService;
import com.elearn.course.service.InstructorService;
import com.elearn.course.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final UserService userService;
    private final CourseService courseService;

    @Override
    public Instructor loadInstructorById(Long instructorId) {
        return instructorRepository.findById(instructorId)
                .orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + instructorId + " not found"));
    }

    @Override
    public Page<InstructorDTO> findInstructorByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Instructor> instructorPage = instructorRepository.findInstructorsByName(name, pageRequest);
        return new PageImpl<>(instructorPage
                .getContent()
                .stream()
                .map(instructor -> instructorMapper.fromInstructor(instructor))
                .collect(Collectors.toList()), pageRequest, instructorPage.getTotalElements());
    }

    @Override
    public InstructorDTO loadInstructorByEmail(String email) {
        return instructorMapper.fromInstructor(instructorRepository.findInstructorByEmail(email));
    }

    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        User user = userService.createUser(instructorDTO.getUser().getEmail(), instructorDTO.getUser().getPassword());
        userService.assignRoleToUser(user.getEmail(), "Instructor");
        Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
        instructor.setUser(user);
        Instructor savedInstructor = instructorRepository.save(instructor);
        return instructorMapper.fromInstructor(savedInstructor);
    }

    @Override
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO) {
        Instructor loadInstructor = loadInstructorById(instructorDTO.getInstructorId());
        Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
        instructor.setUser(loadInstructor.getUser());
        instructor.setCourses(loadInstructor.getCourses());
        Instructor updateInstructor = instructorRepository.save(instructor);
        return instructorMapper.fromInstructor(updateInstructor);
    }

    @Override
    public List<InstructorDTO> fetchInstructors() {
        return instructorRepository.findAll()
                .stream()
                .map(instructor -> instructorMapper.fromInstructor(instructor))
                .collect(Collectors.toList());
    }

    @Override
    public void removeInstructor(Long instructorId) {
    }
}
