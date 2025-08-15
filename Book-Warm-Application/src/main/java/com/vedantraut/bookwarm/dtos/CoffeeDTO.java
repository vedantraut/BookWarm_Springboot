package com.vedantraut.bookwarm.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeDTO {
	private Long coffeeId;
	
	@NotBlank(message="Coffee name cannot be blank!")
	private String name;
	
	@NotBlank(message="Coffee type cannot be blank!")
	private String coffeeType;
	
	@Min(value=20, message = "Coffee price must be at least ₹20")
	private double price;
	
	@Size(min=10, max=1000, message="Please enter the description of coffee within 10-1000 chars!")
	private String description;
	
	private String imageUrl;
}
