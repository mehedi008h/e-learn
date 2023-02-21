package com.elearn.course.service.impl;

import com.elearn.course.modal.Role;
import com.elearn.course.repository.RoleRepository;
import com.elearn.course.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    // create role
    @Override
    public Role createRole(String roleName) {
        var role = Role.builder().name(roleName).build();
        return roleRepository.save(role);
    }
}
