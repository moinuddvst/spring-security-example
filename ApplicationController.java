package com.thymleaf.example.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/auth")
public class ApplicationController {

	@GetMapping("/proccess")
	public String proccess() {
		return "proccessing.....";
	}
}
