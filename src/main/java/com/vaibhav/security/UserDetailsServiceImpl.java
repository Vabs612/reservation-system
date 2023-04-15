package com.vaibhav.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vaibhav.model.MyUserDetails;
import com.vaibhav.model.User;
import com.vaibhav.security.jpa.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.err.println("User invoked   " + username);

		User user = userRepository.getUserByName(username);
		if (user != null) {
			return new MyUserDetails(user);
		} else
			throw new UsernameNotFoundException("Could not find user");

	}

}