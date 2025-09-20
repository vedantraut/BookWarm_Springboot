package com.vedantraut.bookwarm.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long userId;
	
	@NotNull(message="Username cannot be null!")
	private String userName;
	
	@NotNull(message="Password cannot be null!")
	@Pattern(regexp= "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$", message="Please Enter Valid Password!")
	private String password;
	
	@Email(message="Please Enter Valid Email!")
	private String email;
	
	@NotNull(message="Role cannot be null!")
	private String role;
}
