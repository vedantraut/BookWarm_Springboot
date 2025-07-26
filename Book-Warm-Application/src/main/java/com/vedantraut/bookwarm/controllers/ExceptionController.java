package com.vedantraut.bookwarm.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
	
	@GetMapping("/test-exception")
	public String testException() {
		throw new RuntimeException("Something went wrong with Book Warm!");
	}
}
