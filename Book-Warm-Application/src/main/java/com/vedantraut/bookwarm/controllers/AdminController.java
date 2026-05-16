package com.vedantraut.bookwarm.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedantraut.bookwarm.dtos.AuthenticationRequest;
import com.vedantraut.bookwarm.dtos.AuthenticationResponse;
import com.vedantraut.bookwarm.dtos.BookDTO;
import com.vedantraut.bookwarm.dtos.DashboardStatsDTO;
import com.vedantraut.bookwarm.dtos.UserDTO;
import com.vedantraut.bookwarm.services.AdminService;
import com.vedantraut.bookwarm.services.AuthenticationService;
import com.vedantraut.bookwarm.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userservice;

	@Autowired
	AuthenticationService authservice;
	
	@Autowired
	AdminService adminservice;
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "Welcome Admin!";
	}
	
	@GetMapping("/dashboard/stats")
	public DashboardStatsDTO getStats() {
		return adminservice.getDashboardStats();
	}
	
	@PostMapping("/books")
	public ResponseEntity<BookDTO> addBooks(@RequestBody BookDTO bookdto) {
		
		BookDTO b = adminservice.addBook(bookdto);
		
		return ResponseEntity.ok(b);
	}
	
	
	
	
}
