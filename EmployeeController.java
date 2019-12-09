package com.thymleaf.example.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.thymleaf.example.app.model.Employee;
import com.thymleaf.example.app.repository.EmployeeRepository;
import com.thymleaf.example.app.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService service;

	@GetMapping("/employeeRegistration")
	public ModelAndView employeeRegistration() {
		ModelAndView mv = new ModelAndView("employeeregistration");
		mv.addObject("employee", new Employee());
		return mv;
	}

	@PostMapping("/employeeRegistration")
	@PreAuthorize("hasAnyRole('EMPLOYEE')")
	public ModelAndView employeeRegistration(@Valid Employee employee, BindingResult result) {
		ModelAndView mv = new ModelAndView("employeeregistration");
		if (result.hasErrors()) {
			logger.info("========================");
			logger.info("validation error has occured");
			logger.info("validation error while submiting form.");
			mv.setViewName("employeeregistration");
			mv.addObject("employee", employee);
			return mv;
		} else {
			logger.info("======================");
			logger.info("successfully executed");
			Employee employeeResponse = service.employeeRegistration(employee);
			mv.addObject("employee", employeeResponse);
			mv.setViewName("employeeregisterdsuccess");
			return mv;
		}
	}
}
