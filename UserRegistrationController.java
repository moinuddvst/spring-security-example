package com.thymleaf.example.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.thymleaf.example.app.model.Role;
import com.thymleaf.example.app.model.User;
import com.thymleaf.example.app.repository.RoleRepository;
import com.thymleaf.example.app.repository.UserRepository;
import com.thymleaf.example.app.service.UsereService;

@RestController
@RequestMapping("/user/unauth")
public class UserRegistrationController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);
	@Autowired
	private UsereService service;
	List<Role> list = null;

	@GetMapping("/userRegistration")
	public ModelAndView employeeRegistration() {
		ModelAndView mv = new ModelAndView("userregistration");
		logger.info("requesting for user reg page");
		list = new ArrayList<>();
		Role item1 = new Role();
		item1.setRoleid(1);
		item1.setRole("ADMIN");
		list.add(item1);
		Role item2 = new Role();
		item2.setRoleid(2);
		item2.setRole("USER");
		list.add(item2);
		Role item3 = new Role();
		item3.setRoleid(3);
		item3.setRole("EMPLOYEE");
		list.add(item3);
		mv.addObject("checkboxlist", list);
		mv.addObject("user", new User());
		return mv;
	}

	@PostMapping("/userRegistration")
	public ModelAndView employeeRegistration(
			@RequestParam(value = "rolecheckboxitem", required = false) String rolecheckboxitem[], @Valid User user,
			BindingResult result) {
		ModelAndView mv = new ModelAndView("userregistration");
		if (rolecheckboxitem != null) {
			logger.info("checkbox value is not null");
			Set<Role> r1 = new HashSet<>();
			logger.info("name ");
			for (String r : rolecheckboxitem) {
				logger.info("selected roles " + r);
				Role r2 = new Role();
				r2.setRole(r);
				r1.add(r2);
			}
			user.setRoles(r1);
		}
		logger.info("requesting for user reg page submission");
		if (result.hasErrors()) {
			logger.info("========================");
			logger.info("validation error has occured");
			logger.info("validation error while submiting form.");
			mv.setViewName("userregistration");
			mv.addObject("checkboxlist", list);
			mv.addObject("user", user);
			return mv;
		} else {
			logger.info("======================");
			logger.info("successfully executed");
			User userResponse = service.userRegistration(user);
			mv.addObject("user", userResponse);
			mv.setViewName("userregisterdsuccess");
			return mv;
		}
	}
	@GetMapping("/getUserDetails")
	public String getRole() {
		
		List<User> users=userRepository.findAll();
		List<Role> roles=roleRepository.findAll();
		for(User user : users) {
			logger.info("=================user details=============");
			logger.info("userid "+user.getUserid());
			logger.info("username "+user.getUsername());
			logger.info("email "+user.getEmail());
			logger.info("password "+user.getPassword());
			logger.info("roles"+user.getRoles());
			Set<Role> riu = user.getRoles();
			for(Role r : riu) {
				logger.info("=================role inside user=============");
				logger.info("roleid "+r.getRoleid());
				logger.info("role "+r.getRole());

			}
		}
		for(Role role : roles) {
			logger.info("=================roles  details=============");
			logger.info("roleid "+role.getRoleid());
			logger.info("role "+role.getRole());
		}
		return "success";
	}

}
