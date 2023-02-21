package com.elearn.course.repository;

import com.elearn.course.modal.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // find role by name
    Role findByName(String name);
}
