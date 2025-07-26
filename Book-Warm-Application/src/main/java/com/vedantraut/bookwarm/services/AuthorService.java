package com.vedantraut.bookwarm.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedantraut.bookwarm.dtos.AuthorDTO;
import com.vedantraut.bookwarm.dtos.BookDTO;
import com.vedantraut.bookwarm.entity.Author;
import com.vedantraut.bookwarm.entity.Book;
import com.vedantraut.bookwarm.exceptions.AuthorNotFoundException;
import com.vedantraut.bookwarm.repository.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository authorrepository;

	public List<Author> getAllAuthors() {
		
		List<Author> allAuthors = authorrepository.findAll();
		return allAuthors;
	}

	public Author saveAuthor(AuthorDTO authordto) {
		
		Author author = new Author();
		
		author.setName(authordto.getName());
		author.setBio(authordto.getBio());
		
		List<BookDTO> bookdtos = authordto.getBooks();
		List<Book> bookEntities = new ArrayList<>();
		
		for(BookDTO bookdto: bookdtos) {
			Book newBook = new Book();
		
			newBook.setTitle(bookdto.getTitle());
			newBook.setPrice(bookdto.getPrice());
			newBook.setIsbn(bookdto.getIsbn());
			newBook.setAuthor(author);
			
			bookEntities.add(newBook);
		}
		
		author.setBooks(bookEntities);
		//
		
		authorrepository.save(author);
		
		return author;
	}

	public Author getAuthorById(long id) {
		
//		Author auth = authorrepository.findById(id).orElse(null);
		Author auth = authorrepository.findById(id)
						.orElseThrow(() -> new AuthorNotFoundException("Author do not exist for id - "+id));
		
		return auth;
	}

	public void deleteById(long id) {
		
		authorrepository.deleteById(id);
	}

	public Author updateAuthor(AuthorDTO authordto) {
		
		Author existingAuthor = authorrepository.findById(authordto.getId()).get();
		
		existingAuthor.setName(authordto.getName());
		existingAuthor.setBio(authordto.getBio());
//		existingAuthor.setBooks(authordto.getBooks());
		
		List<BookDTO> bookdtos = authordto.getBooks();
		List<Book> bookEntities = new ArrayList<>();
		
		for(BookDTO bookdto: bookdtos) {
			Book newBook = new Book();
		
			newBook.setTitle(bookdto.getTitle());
			newBook.setPrice(bookdto.getPrice());
			newBook.setIsbn(bookdto.getIsbn());
			newBook.setAuthor(existingAuthor);
			
			bookEntities.add(newBook);
		}
		
		existingAuthor.setBooks(bookEntities);
				
		authorrepository.save(existingAuthor);
		
		System.out.println("Author to be updated -- "+ existingAuthor);
		
		return existingAuthor;
	}
	
}
