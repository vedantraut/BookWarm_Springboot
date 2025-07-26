package com.vedantraut.bookwarm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedantraut.bookwarm.dtos.AuthorDTO;
import com.vedantraut.bookwarm.entity.Author;
import com.vedantraut.bookwarm.services.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
	
	@Autowired
	AuthorService authorservice;
	
	@GetMapping("/getAllAuthors")
	public ResponseEntity<List<Author>> getAllAuthors(){
		
//		return ResponseEntity.ok(authorservice.getAllAuthors());
		
		ResponseEntity<List<Author>> resp =  ResponseEntity.ok(authorservice.getAllAuthors());
		System.out.println("Response status code while getting all the authors -- "+resp.getStatusCode());
		
		return resp;
	}
	
	@PostMapping("/createAuthor")
	public ResponseEntity<Author> createAuthors(@RequestBody @Valid AuthorDTO authordto){
		
		Author author = authorservice.saveAuthor(authordto);
		
		return ResponseEntity.ok(author);
	}
	
	@GetMapping("/getAuthor")
	public ResponseEntity<Author> getAuthor(@RequestParam int id){
				
		Author author = authorservice.getAuthorById(id);
		
		if(author == null) {
			System.out.println("Author at id - "+id+" is not found...");
			return ResponseEntity.notFound().build();
		}
		
		System.out.println("Author at id - "+id+" is "+author.getName());
		return ResponseEntity.ok(author);
	}
	
	@DeleteMapping("/deleteAuthor/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable long id){
		
		authorservice.deleteById(id);
		System.out.println("Author is deleted successfully!");
		
		return ResponseEntity.ok("Author deleted!");
	}
	
	@PutMapping("/updateAuthor")
	public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authordto){
		
		authorservice.updateAuthor(authordto);
		
		return ResponseEntity.ok(authordto);
	}
}
