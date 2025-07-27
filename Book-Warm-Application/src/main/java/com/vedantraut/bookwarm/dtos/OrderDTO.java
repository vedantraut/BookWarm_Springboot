package com.vedantraut.bookwarm.dtos;

import java.util.List;

import com.vedantraut.bookwarm.entity.Book;
import com.vedantraut.bookwarm.entity.Coffee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	@NotNull(message = "Book ID is required")
	private Long bookId;
	
	@NotNull(message = "Coffee ID is required")
	private Long coffeeId;
}
