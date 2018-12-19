package com.example.cat.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cat.user.entity.UserLogin;

public interface UserLoginsRepository extends JpaRepository<UserLogin, Long> {

	UserLogin findByUsername(String username);
}
