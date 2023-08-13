package com.excercise.lab7.repository;

import org.springframework.data.repository.CrudRepository;

import com.excercise.lab7.object.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
