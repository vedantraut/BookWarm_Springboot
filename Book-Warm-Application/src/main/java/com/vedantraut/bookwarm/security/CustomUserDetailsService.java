package com.vedantraut.bookwarm.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vedantraut.bookwarm.entity.Users;
import com.vedantraut.bookwarm.exceptions.UserNotFoundException;
import com.vedantraut.bookwarm.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userrepository.findByEmail(username)
						.orElseThrow(() -> new UserNotFoundException("User Not Found!"));
		
		return new User(user.getEmail(), 
						user.getPassword(), 
						new ArrayList<>()
		);
	}

}
