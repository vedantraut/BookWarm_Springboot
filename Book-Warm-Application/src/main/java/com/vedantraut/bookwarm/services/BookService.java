package com.vedantraut.bookwarm.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedantraut.bookwarm.dtos.BookDTO;
import com.vedantraut.bookwarm.entity.Author;
import com.vedantraut.bookwarm.entity.Book;
import com.vedantraut.bookwarm.exceptions.AuthorNotFoundException;
import com.vedantraut.bookwarm.exceptions.BookNotFoundException;
import com.vedantraut.bookwarm.repository.AuthorRepository;
import com.vedantraut.bookwarm.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookrepo;
	
	@Autowired
	private AuthorRepository authorrepo;
	
	public List<Book> getAllBooks() {
		return bookrepo.findAll();
	}
	
	public Book getBookById(Long id) {
		
		System.out.println("Finding book by id - "+id);
		
		Book book = bookrepo.findById(id)
					.orElseThrow(() -> new BookNotFoundException("Book not found with id - "+id));
		
		return book;
	}
	
	public void deleteBook(Long id) {
		bookrepo.deleteById(id);
	}
	
	public Book saveBook(BookDTO bookdto) {
		
		System.out.println("Book to be saved -- "+ bookdto);
		
		Book newbook = new Book();
		
		newbook.setTitle(bookdto.getTitle());
		newbook.setPrice(bookdto.getPrice());
		newbook.setIsbn(bookdto.getIsbn());
		
		Author author = authorrepo.findById(bookdto.getAuthor_id())
				.orElseThrow(() -> new AuthorNotFoundException("Author not found"));
		
		newbook.setAuthor(author);
		
		return bookrepo.save(newbook);
	}
	
	public Book updateBook(BookDTO bookdto) {
		
		System.out.println("Book to be updated -- "+ bookdto);
		
		Book existingBook = bookrepo.findById(bookdto.getId()).get();
		
		existingBook.setTitle(bookdto.getTitle());
		existingBook.setPrice(bookdto.getPrice());
		existingBook.setIsbn(bookdto.getIsbn());
		
		Author author = authorrepo.findById(bookdto.getAuthor_id())
				.orElseThrow(() -> new RuntimeException("Author not found"));
		
		existingBook.setAuthor(author);
		
		
		return bookrepo.save(existingBook);
	}

	public List<Book> sortBooks(String sortBy, String order) {
		
		List<Book> allBooks = bookrepo.findAll();
		
		System.out.println("Before Sorting allBooks by -> "+sortBy);
		
		Comparator<Book> comparator = switch(sortBy) {
			case "title" -> Comparator.comparing(Book::getTitle);
			case "price" -> Comparator.comparing(Book::getPrice);
			default -> throw new IllegalArgumentException("Invalid Sort Field!"); 
		};
		
		if(order.equalsIgnoreCase("desc")) {
			comparator = comparator.reversed();
		}
		
		allBooks.sort(comparator);
		
		System.out.println("After Sorting allBooks by -> "+sortBy);
		
		return allBooks;
	}
}
