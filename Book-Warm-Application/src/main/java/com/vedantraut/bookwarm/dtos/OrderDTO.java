package com.vedantraut.bookwarm.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	@NotNull(message = "User ID is required")
	private Long userId;
	
	@NotNull(message = "Book ID is required")
	private Long bookId;
	
	@NotNull(message = "Coffee ID is required")
	private Long coffeeId;
}
