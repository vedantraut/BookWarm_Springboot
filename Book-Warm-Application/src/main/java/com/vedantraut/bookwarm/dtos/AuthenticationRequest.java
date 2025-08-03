package com.vedantraut.bookwarm.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vedantraut.bookwarm.entity.Book;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
	private String emailId;
	private String password;
}
