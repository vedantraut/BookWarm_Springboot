package com.vedantraut.bookwarm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long coffeeId;
	
	@NotBlank(message="Coffee name cannot be blank!")
	private String name;
	
	@NotBlank(message="Coffee type cannot be blank!")
	private String coffeeType;
	
	@Min(value=20, message = "Coffee price must be at least ₹20")
	private double price;
	
	@Size(min=10, max=100, message="Please enter the description of coffee within 10-100 chars!")
	private String description;
}
