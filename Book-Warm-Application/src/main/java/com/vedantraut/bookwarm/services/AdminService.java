package com.vedantraut.bookwarm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedantraut.bookwarm.dtos.AuthorDTO;
import com.vedantraut.bookwarm.dtos.BookDTO;
import com.vedantraut.bookwarm.dtos.CoffeeDTO;
import com.vedantraut.bookwarm.dtos.DashboardStatsDTO;
import com.vedantraut.bookwarm.entity.Author;
import com.vedantraut.bookwarm.entity.Book;
import com.vedantraut.bookwarm.entity.Coffee;
import com.vedantraut.bookwarm.repository.BookRepository;
import com.vedantraut.bookwarm.repository.OrderRepository;
import com.vedantraut.bookwarm.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class AdminService {
	
	@Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
	private BookService bookservice;
    
    @Autowired
    private CoffeeService coffeeservice;
    
    @Autowired
	AuthorService authorservice;

	public DashboardStatsDTO getDashboardStats() {
		
		long users = userRepository.count(); 
		long books = bookRepository.count(); 
		long orders = orderRepository.count(); 
		
		return new DashboardStatsDTO(users, books, orders);
	}

	public BookDTO addBook(BookDTO bookdto) {
		
		Book b = bookservice.saveBook(bookdto);
		
		bookdto.setId(b.getBookId());
		
		return bookdto;
	}

	public CoffeeDTO addCoffee(CoffeeDTO coffeedto) {
		
		Coffee c = coffeeservice.saveCoffee(coffeedto);
		
		coffeedto.setCoffeeId(c.getCoffeeId());
		
		return coffeedto;
	}

	public AuthorDTO addAuthor(@Valid AuthorDTO authordto) {
		
		Author a = authorservice.saveAuthor(authordto);
		
		authordto.setId(a.getAuthorId());
		
		return authordto;
	}

	public List<BookDTO> getBooks() {
		List<BookDTO> books = bookservice.getAllBooks();
		
		return books;
	}

	public List<CoffeeDTO> getCoffees() {
		List<CoffeeDTO> coffee = coffeeservice.fetchAllCoffee();
		
		return coffee;
	}

	public List<AuthorDTO> getAuthors() {
		List<AuthorDTO> authors = authorservice.getAllAuthors();
		
		return authors;
	}
	
	

}
