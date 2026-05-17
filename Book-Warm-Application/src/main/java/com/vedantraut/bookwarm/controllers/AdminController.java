package com.vedantraut.bookwarm.controllers;

import java.util.HashMap;
import java.util.List;
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
import com.vedantraut.bookwarm.dtos.AuthorDTO;
import com.vedantraut.bookwarm.dtos.BookDTO;
import com.vedantraut.bookwarm.dtos.CoffeeDTO;
import com.vedantraut.bookwarm.dtos.DashboardStatsDTO;
import com.vedantraut.bookwarm.dtos.UserDTO;
import com.vedantraut.bookwarm.services.AdminService;
import com.vedantraut.bookwarm.services.AuthenticationService;
import com.vedantraut.bookwarm.services.AuthorService;
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
	public ResponseEntity<BookDTO> addBook(@RequestBody @Valid BookDTO bookdto) {
		
		BookDTO b = adminservice.addBook(bookdto);
		
		return ResponseEntity.ok(b);
	}
	
	@PostMapping("/coffees")
	public ResponseEntity<CoffeeDTO> addCoffee(@RequestBody @Valid CoffeeDTO coffeedto) {
		
		CoffeeDTO c = adminservice.addCoffee(coffeedto);
		
		return ResponseEntity.ok(c);
	}
	
	@PostMapping("/authors")
	public ResponseEntity<AuthorDTO> addAuthor(@RequestBody @Valid AuthorDTO authordto) {
		
		AuthorDTO a = adminservice.addAuthor(authordto);
		
		return ResponseEntity.ok(a);
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<BookDTO>> getBooks() {
		
		List<BookDTO> listOfAllBooks = adminservice.getBooks();
		
		return ResponseEntity.ok(listOfAllBooks);
	}
	
	@GetMapping("/coffees")
	public ResponseEntity<List<CoffeeDTO>> getCoffees() {
		
		List<CoffeeDTO> listOfAllCoffee = adminservice.getCoffees();
		
		return ResponseEntity.ok(listOfAllCoffee);
	}
	
	@GetMapping("/authors")
	public ResponseEntity<List<AuthorDTO>> getAuthors() {
		
		List<AuthorDTO> listOfAllAuthors = adminservice.getAuthors();
		
		return ResponseEntity.ok(listOfAllAuthors);
	}
	
	
	
}
