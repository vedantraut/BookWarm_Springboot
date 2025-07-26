package com.vedantraut.bookwarm.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private Long id;
	
	@NotBlank(message="Book Title cannot be blank!")
	private String title;
	
	@Pattern(regexp="^[0-9]{3}-[A-Z]{2}[0-9]{1,}$", message="isbn should be format like 111-HP1")
    private String isbn;
    
    @Min(value=100, message = "Book price must be at least ₹100")
    private Double price;
    private Long author_id;
}
