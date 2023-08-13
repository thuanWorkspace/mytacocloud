package com.excercise.lab7.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excercise.lab7.object.User;
import com.excercise.lab7.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service

public class UserRepositoryUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		System.out.println("run my default load usernaem.");
		User user = userRepo.findByUsername(username);
		if (user != null) {
			user.getAuthorities().forEach( c -> System.out.println(c));
			return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

}
