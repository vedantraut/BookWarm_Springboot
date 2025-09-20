package com.vedantraut.bookwarm.dtos;

import lombok.Data;

@Data
public class RegistertionRequest {
	private String username;
	private String password;
	private String email;
	private String role;
}
