package com.vedantraut.bookwarm.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vedantraut.bookwarm.entity.Book;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
	private Long id;
	
	@NotBlank(message = "Author name cannot be empty!")
	private String name;
	
	@Size(min=10, max=100, message="Please enter the bio within 10-100 chars!")
	private String bio;
	
	@Valid
	private List<BookDTO> books;
}
