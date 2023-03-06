package com.elearn.course.service.impl;

import com.elearn.course.modal.Role;
import com.elearn.course.modal.User;
import com.elearn.course.repository.RoleRepository;
import com.elearn.course.repository.UserRepository;
import com.elearn.course.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // create user
    @Override
    public User createUser(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        return userRepository.save(new User(email, encodedPassword));
    }

    // find user by email
    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // assign role to user
    @Override
    public void assignRoleToUser(String email, String name) {
        User user = loadUserByEmail(email);
        Role role = roleRepository.findByName(name);
        user.assignRoleToUser(role);

    }
}
