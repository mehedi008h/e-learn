package com.elearn.course.service;

import com.elearn.course.modal.User;

public interface UserService {
    // create user
    User createUser(String email, String password);
    // load user by email
    User loadUserByEmail(String email);
    // assign role
    void assignRoleToUser(String email, String name);

}
