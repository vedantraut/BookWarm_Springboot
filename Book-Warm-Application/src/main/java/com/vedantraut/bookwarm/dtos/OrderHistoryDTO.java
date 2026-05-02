package com.vedantraut.bookwarm.dtos;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryDTO {
	private Long orderId;
	private String bookName;
	private String imageUrl;
	private String coffee;
	private Double totalPrice;
	private LocalDateTime orderDate;
	
}
