package com.vedantraut.bookwarm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedantraut.bookwarm.dtos.BookDTO;
import com.vedantraut.bookwarm.dtos.DashboardStatsDTO;
import com.vedantraut.bookwarm.entity.Book;
import com.vedantraut.bookwarm.repository.BookRepository;
import com.vedantraut.bookwarm.repository.OrderRepository;
import com.vedantraut.bookwarm.repository.UserRepository;

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
	
	

}
