package com.vaibhav.security.jpa;

import org.springframework.data.repository.CrudRepository;

import com.vaibhav.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User getUserByName(String username);

	public User findByEmail(String email);

}