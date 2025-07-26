package com.vedantraut.bookwarm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Book Title cannot be blank!")
	private String title;
	private String isbn;
	
	@Min(value=100, message = "Book price must be at least ₹100")
	private double price;
	
	@ManyToOne()
	@JoinColumn(name="author_id")
//	@JsonManagedReference
	
//	This specific field will be excluded from the JSON output. 
//	This is crucial for preventing infinite recursion, 
//	as it breaks the cycle by ensuring that the "child" or "many" side of the 
//	relationship doesn't serialize back to its parent, 
//	which would then serialize back to its children, and so on.
	@JsonBackReference 
	private Author author;
}
