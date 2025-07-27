package com.vedantraut.bookwarm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.vedantraut.bookwarm.dtos.BookDTO;
import com.vedantraut.bookwarm.entity.Book;
import com.vedantraut.bookwarm.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookService bookservice;
	
	@PostMapping("/createBook")
	public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO bookdto) {
		
		Book b = bookservice.saveBook(bookdto);
		
		bookdto.setId(b.getBookId());
		
		return ResponseEntity.ok(bookdto);
	}
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		
		List<Book> b = bookservice.getAllBooks();
		
		return ResponseEntity.ok(b);
	}
	
	@GetMapping("/getBook")
	public ResponseEntity<Book> getBookById(@RequestParam Long id) {
		
		Book b = bookservice.getBookById(id);
		
		if (b == null) {
			System.out.println("Book at id - "+id+" not found...");
	        return ResponseEntity.notFound().build();
	    }
		
		System.out.println("Book at id - "+id+" -- "+b.getTitle());
		return ResponseEntity.ok(b);
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id) {
		bookservice.deleteBook(id);
		
		System.out.println("Deleted Successfully!");
		
//		return ResponseEntity.noContent().build();
		return ResponseEntity.ok("Deleted!");
	} 
	
	@PutMapping("/updateBook")
	public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookdto){
		
		bookservice.updateBook(bookdto);
		return  ResponseEntity.ok(bookdto);
	} 
	
	@GetMapping("/sort")
	public ResponseEntity<List<Book>> sortBooks(@RequestParam String sortBy, @RequestParam String order) {
		
		List<Book> books = bookservice.sortBooks(sortBy, order);
	
		return ResponseEntity.ok(books);
	}
}
