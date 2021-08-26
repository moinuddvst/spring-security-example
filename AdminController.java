package com.thymleaf.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thymleaf.example.app.model.User;
import com.thymleaf.example.app.repository.UserRepository;

@RestController
@RequestMapping("/secure/rest")
//author Moin Uddin
public class AdminController {

	@Autowired
	private UserRepository userRepostory;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/admin/add")
	public String addUserByAdmin(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepostory.save(user);
		return "user added successfully";
	}
}
