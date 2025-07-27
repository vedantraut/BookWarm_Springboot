package com.vedantraut.bookwarm.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long authorId;
	private String name;
	private String bio;
	
	@OneToMany(mappedBy="author", cascade=CascadeType.ALL)
//	@JsonBackReference
	
//	The details of that referenced object will be included in the JSON output. 
//	This is typically applied to the "parent" side of a parent-child relationship 
//	or the "one" side of a one-to-many relationship.
	@JsonManagedReference 
	private List<Book> books;
}
