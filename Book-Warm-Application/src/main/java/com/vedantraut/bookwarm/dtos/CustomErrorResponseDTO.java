package com.vedantraut.bookwarm.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponseDTO {
	private int status;
    private String error;
	private String message;
	private String details;
	private LocalDateTime timestamp;	
}
