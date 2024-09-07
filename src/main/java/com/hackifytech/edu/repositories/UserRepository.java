package com.hackifytech.edu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackifytech.edu.models.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	
}