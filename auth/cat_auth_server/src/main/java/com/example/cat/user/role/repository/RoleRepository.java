package com.example.cat.user.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cat.user.role.Role;
import java.lang.String;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
