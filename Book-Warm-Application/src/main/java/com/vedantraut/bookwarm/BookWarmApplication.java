package com.vedantraut.bookwarm;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vedantraut.bookwarm.entity.Author;
import com.vedantraut.bookwarm.entity.Book;
import com.vedantraut.bookwarm.repository.AuthorRepository;

@SpringBootApplication
public class BookWarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookWarmApplication.class, args);
	}
	
	/*
	@Bean
	public CommandLineRunner demoBookWarmData(AuthorRepository authorRepo) {
		return args -> {
		
			Author author = new Author();
			author.setName("J.K. Rowling");
			author.setBio("British author, best known for the Harry Potter series");
			
			Book book1 = new Book();
			book1.setTitle("Harry Potter and The Sorcerer's Stone");
			book1.setIsbn("111-HP1");
			book1.setPrice(499.0);
			book1.setAuthor(author);
			
			Book book2 = new Book();
			book2.setTitle("Harry Potter and The Chamber of Secrets");
			book2.setIsbn("222-HP2");
			book2.setPrice(549.0);
			book2.setAuthor(author);
			
			Book book3 = new Book();
			book3.setTitle("Harry Potter and The Prisoner Of Azkaban");
			book3.setIsbn("333-HP3");
			book3.setPrice(529.0);
			book3.setAuthor(author);
			
			Book book4 = new Book();
			book4.setTitle("Harry Potter and The Goblet Of Fire");
			book4.setIsbn("444-HP4");
			book4.setPrice(599.0);
			book4.setAuthor(author);
			
			
			author.setBooks(Arrays.asList(book1, book2, book3, book4));
			
			authorRepo.save(author);
			
			System.out.println("X --- SAMPLE DATA SAVED SUCCESSFULLY --- X");
		};		
	}
	 */

}
