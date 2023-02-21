package com.elearn.course.repository;

import com.elearn.course.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // find user by email
    User findByEmail(String email);
}
