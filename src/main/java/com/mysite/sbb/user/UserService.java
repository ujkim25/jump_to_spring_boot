package com.mysite.sbb.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	final UserRepository userRepository;
	
	SiteUser createUser(String username, String email, String password){
		SiteUser siteUser = new SiteUser();
		siteUser.setUsername(username);
		siteUser.setEmail(email);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		siteUser.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(siteUser);
		return siteUser;
	}
}
